package com.example.miniappexoplayer.ui.exoplayer

import android.graphics.Color
import android.net.Uri
import android.os.Bundle
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.example.miniappexoplayer.base.BaseActivity
import com.example.miniappexoplayer.databinding.ActivityExoPlayerBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.util.Constants.Constants.MOVIESITEM
import com.example.miniappexoplayer.util.Constants.Constants.STREAM_URL
import com.google.android.exoplayer2.MediaItem
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory
import com.google.android.exoplayer2.source.DefaultMediaSourceFactory
import com.google.android.exoplayer2.source.MediaSource
import com.google.android.exoplayer2.source.MediaSourceFactory
import com.google.android.exoplayer2.source.ProgressiveMediaSource
import com.google.android.exoplayer2.source.dash.DashMediaSource
import com.google.android.exoplayer2.source.dash.DefaultDashChunkSource
import com.google.android.exoplayer2.upstream.DataSource
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSource
import com.google.android.exoplayer2.util.Util
import android.annotation.SuppressLint
import android.view.View


class ExoPlayerActivity : BaseActivity<ActivityExoPlayerBinding>() {

    private lateinit var simpleExoPlayer: SimpleExoPlayer
    private lateinit var mediaDataSourceFactory: DataSource.Factory

    private lateinit var movieItem: Movies

    override fun getViewBinding(): ActivityExoPlayerBinding = ActivityExoPlayerBinding.inflate(layoutInflater)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        intent.extras.let {
            movieItem = it?.getParcelable(MOVIESITEM)!!
        }
        binding.movies = movieItem
    }


    private fun initializePlayer() {

        mediaDataSourceFactory = DefaultDataSourceFactory(this, Util.getUserAgent(this, "beinVideoPlayer"))

        val uri = Uri.parse(STREAM_URL)

        val mediaSource  = buildMediaSource(uri)

        val mediaSourceFactory: MediaSourceFactory = DefaultMediaSourceFactory(mediaDataSourceFactory)

        simpleExoPlayer = SimpleExoPlayer.Builder(this)
            .setMediaSourceFactory(mediaSourceFactory)
            .build()


        binding.playerView.setShutterBackgroundColor(Color.TRANSPARENT)
        binding.playerView.player = simpleExoPlayer
        simpleExoPlayer.playWhenReady = true
        binding.playerView.requestFocus()

        simpleExoPlayer.addMediaSource(mediaSource)
    }

    private fun releasePlayer() {
        simpleExoPlayer.release()
    }

    //device-API based classification
    override fun onStart() {
        super.onStart()
        if (Util.SDK_INT > 23) {
            initializePlayer()
        }

    }

    override fun onResume() {
        super.onResume()
        hideSystemUi()
        if (Util.SDK_INT <= 23) {
            initializePlayer()
        }

    }

    override fun onPause() {
        super.onPause()
        if (Util.SDK_INT <= 23) releasePlayer()
    }

    override fun onStop() {
        super.onStop()
        if (Util.SDK_INT > 23) releasePlayer()
    }

    private fun buildMediaSource(uri: Uri): MediaSource {
        val lastPathSegment = uri.lastPathSegment
        return if (lastPathSegment?.contains("mp4") == true) {
            ProgressiveMediaSource.Factory(DefaultDataSourceFactory(this, "channel-video-player"))
                .createMediaSource(MediaItem.fromUri(uri))
        }
        else if (lastPathSegment?.contains("m3u8") == true) {
            HlsMediaSource.Factory(DefaultHttpDataSource.Factory())
                .createMediaSource(MediaItem.fromUri(uri))
        } else if (lastPathSegment?.contains("ts") == true) {
            val defaultExtractorsFactory = DefaultExtractorsFactory()
            defaultExtractorsFactory.setTsExtractorFlags(DefaultTsPayloadReaderFactory.FLAG_DETECT_ACCESS_UNITS)
            return ProgressiveMediaSource.Factory(
                DefaultDataSourceFactory(this, "channel-video-player"),
                defaultExtractorsFactory).createMediaSource(MediaItem.fromUri(uri))
        } else {
            val dashChunkSourceFactory = DefaultDashChunkSource.Factory(
                DefaultHttpDataSource.Factory()
            )
            val manifestDataSourceFactory = DefaultHttpDataSource.Factory()
            DashMediaSource.Factory(dashChunkSourceFactory, manifestDataSourceFactory)
                .createMediaSource(MediaItem.fromUri(uri))
        }
    }

    // This is just an implementation detail to have a pure full screen experience.
    @SuppressLint("InlinedApi")
    private fun hideSystemUi() {
        binding.playerView.setSystemUiVisibility(
            View.SYSTEM_UI_FLAG_LOW_PROFILE
                    or View.SYSTEM_UI_FLAG_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
        )
    }
}