package com.example.miniappexoplayer.ui.mainpage

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.library.baseAdapters.BR
import com.example.miniappexoplayer.base.BaseActivity
import com.example.miniappexoplayer.databinding.ActivityMainBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.ItemsClickListener
import com.example.miniappexoplayer.ui.adapters.*
import com.example.miniappexoplayer.ui.animation.ZoomInPageTransformer
import com.example.miniappexoplayer.ui.exoplayer.ExoPlayerActivity
import com.example.miniappexoplayer.ui.secondpage.SecondPageActivity
import com.example.miniappexoplayer.util.Constants.Constants.MOVIESITEM
import com.example.miniappexoplayer.util.Constants.Constants.TABITEM
import com.example.miniappexoplayer.util.EventObserver
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.layout_toolbar.view.*
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainPageActivity : BaseActivity<ActivityMainBinding>(), ItemsClickListener {

    private val viewModel by viewModel<MainPageViewModel>()


    private lateinit var viewpagerAdapter: ViewPagerAdapter
    private lateinit var actionMovieAdapter: ActionMovieAdapter
    private lateinit var dramaMovieAdapter: DramaMovieAdapter
    private lateinit var animationMovieAdapter: AnimationMovieAdapter
    private lateinit var comedyMovieAdapter: ComedyMovieAdapter

    override fun getViewBinding(): ActivityMainBinding = ActivityMainBinding.inflate(layoutInflater).also {
        it.listContent.run {
            setVariable(BR.viewmodel, viewModel)
            lifecycleOwner = this@MainPageActivity
        }
        it.viewpagerlayout.run {
            setVariable(BR.viewmodel, viewModel)
            lifecycleOwner = this@MainPageActivity
        }
        it.firstToolBar.run {
            setVariable(BR.viewmodel, viewModel)
            lifecycleOwner = this@MainPageActivity
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.mainToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        setUpRecyclerViews()
        setUpViewPagerAdapter()
        viewModel.getGenres()
        viewModel.getMovies()

        viewModel.getDataAsIntent.observe(this, EventObserver{ allList ->
            val intent = Intent(this, SecondPageActivity::class.java)
            intent.putExtra(TABITEM, allList as ArrayList<out Movies>)
            startActivity(intent)
        })

    }

    private fun setUpViewPagerAdapter() {
        viewpagerAdapter = ViewPagerAdapter(this)
        binding.viewpagerlayout.collapsedViewpager.run {
            adapter = viewpagerAdapter
            setPageTransformer(ZoomInPageTransformer())
        }

        TabLayoutMediator(binding.viewpagerlayout.tabLayout, binding.viewpagerlayout.collapsedViewpager) {
                tab, position -> }.attach()

    }



    private fun setUpRecyclerViews() {
        actionMovieAdapter = ActionMovieAdapter(this)
        dramaMovieAdapter = DramaMovieAdapter(this)
        animationMovieAdapter = AnimationMovieAdapter(this)
        comedyMovieAdapter = ComedyMovieAdapter(this)
        binding.listContent.run {
            recyclerView1.adapter = actionMovieAdapter
            recyclerView2.adapter = dramaMovieAdapter
            recyclerView3.adapter = animationMovieAdapter
            recyclerView4.adapter = comedyMovieAdapter
        }
    }


    override fun onItemClick(movieItem: Movies) {
        Toast.makeText(this, "You clicked", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ExoPlayerActivity::class.java)
        intent.putExtra(MOVIESITEM, movieItem)
        startActivity(intent)
    }
}