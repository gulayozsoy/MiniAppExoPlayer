package com.example.miniappexoplayer.ui.secondpage

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.example.miniappexoplayer.databinding.FragmentForeignMoviesBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.ItemsClickListener
import com.example.miniappexoplayer.ui.adapters.*
import com.example.miniappexoplayer.ui.exoplayer.ExoPlayerActivity
import com.example.miniappexoplayer.util.Constants


class ForeignMoviesFragment : Fragment(), ItemsClickListener {

    private val sharedViewModel: SecondPageViewModel by activityViewModels()

    private var _binding: FragmentForeignMoviesBinding? = null

    private val binding get() = _binding!!

    private lateinit var actionMovieAdapter: ActionMovieAdapter
    private lateinit var dramaMovieAdapter: DramaMovieAdapter
    private lateinit var animationMovieAdapter: AnimationMovieAdapter
    private lateinit var comedyMovieAdapter: ComedyMovieAdapter

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentForeignMoviesBinding.inflate(inflater, container, false)
        binding.viewmodel = sharedViewModel
        setUpRecyclerViews()
        return binding.root
    }


    private fun setUpRecyclerViews() {
        actionMovieAdapter = ActionMovieAdapter(this)
        dramaMovieAdapter = DramaMovieAdapter(this)
        animationMovieAdapter = AnimationMovieAdapter(this)
        comedyMovieAdapter = ComedyMovieAdapter(this)
        binding.recyclerView1.adapter = actionMovieAdapter
        binding.recyclerView2.adapter = dramaMovieAdapter
        binding.recyclerView3.adapter = animationMovieAdapter
        binding.recyclerView4.adapter = comedyMovieAdapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onItemClick(movieItem: Movies) {
        val intent = Intent(activity, ExoPlayerActivity::class.java)
        intent.putExtra(Constants.Constants.MOVIESITEM, movieItem)
        startActivity(intent)
    }


}