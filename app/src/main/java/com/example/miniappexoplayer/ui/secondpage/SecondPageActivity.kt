package com.example.miniappexoplayer.ui.secondpage

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.miniappexoplayer.base.BaseActivity
import com.example.miniappexoplayer.databinding.ActivitySecondPageBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.adapters.SecondViewPagerAdapter
import com.example.miniappexoplayer.util.Constants.Constants.TABITEM
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class SecondPageActivity : BaseActivity<ActivitySecondPageBinding>() {

    private var allList = ArrayList<Movies>()

    private val viewModel by viewModels<SecondPageViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setSupportActionBar(binding.secondToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
        binding.secondToolbar.bottomlayout.visibility = View.GONE

        setupViewPager()

        if(intent.extras != null) {
            allList = intent.getSerializableExtra(TABITEM) as ArrayList<Movies>
            viewModel.importData(allList)
        }

    }

    override fun getViewBinding(): ActivitySecondPageBinding = ActivitySecondPageBinding.inflate(layoutInflater)

    private fun setupViewPager() {
        val secondViewPagerAdapter = SecondViewPagerAdapter(supportFragmentManager)
        secondViewPagerAdapter.apply {
            addFragment(ForeignMoviesFragment(), "Yabancı")
            addFragment(LocalMoviesFragment(), "Yerli")
            addFragment(OtherFragment(),"Betimlemeler")
        }

        binding.secondviewpager.run {
            adapter = secondViewPagerAdapter
            offscreenPageLimit = 3
        }

        binding.secondTabLayout.setupWithViewPager(binding.secondviewpager)

    }
}