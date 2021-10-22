package com.example.miniappexoplayer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.adapters.*
import com.example.miniappexoplayer.util.Constants.Constants.BIG_POSTER_URL
import com.example.miniappexoplayer.util.Constants.Constants.POSTER_URL


object BindingAdapters {

    @JvmStatic
    @BindingAdapter("imageUrl")
    fun loadImage(imageView: ImageView, url:String? ) {
        if (url != null) {
            if(url.isNotEmpty()) {
                Glide.with(imageView.context)
                    .load(POSTER_URL + url)
                    .into(imageView)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("imageBiggerUrl")
    fun loadBiggerImage(imageView: ImageView, url:String? ) {
        if (url != null) {
            if(url.isNotEmpty()) {
                Glide.with(imageView.context)
                    .load(BIG_POSTER_URL + url)
                    .into(imageView)
            }
        }
    }

    @JvmStatic
    @BindingAdapter("list")
    fun bindList(recyclerView: RecyclerView, list: List<Movies>?) {

        when(recyclerView.adapter) {
            is DramaMovieAdapter -> {
                if (list != null) {
                    (recyclerView.adapter as DramaMovieAdapter).updateItems(list)
                }
            }
            is ActionMovieAdapter -> {
                if (list != null) {
                    (recyclerView.adapter as ActionMovieAdapter).updateItems(list)
                }
            }
            is AnimationMovieAdapter -> {
                if (list != null) {
                    (recyclerView.adapter as AnimationMovieAdapter).updateItems(list)
                }
            }
            is ComedyMovieAdapter -> {
                if (list != null) {
                    (recyclerView.adapter as ComedyMovieAdapter).updateItems(list)
                }
            }
        }
    }

    @JvmStatic
    @BindingAdapter("viewpager")
    fun bindViewPager(viewPager: ViewPager2, list: List<Movies>?) {

        if(viewPager.adapter is ViewPagerAdapter) {
            if (list != null) {
                (viewPager.adapter as ViewPagerAdapter).updateItems(list)
            }
        }
    }
}
