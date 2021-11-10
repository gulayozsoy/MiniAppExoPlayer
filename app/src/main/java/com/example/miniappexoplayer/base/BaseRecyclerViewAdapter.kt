package com.example.miniappexoplayer.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.miniappexoplayer.databinding.LayoutMovieItemBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.ItemsClickListener
import kotlinx.coroutines.CoroutineScope
import androidx.recyclerview.widget.AsyncListDiffer
import java.util.Collections.addAll


abstract class BaseRecyclerViewAdapter(
    private val clickListener: ItemsClickListener): RecyclerView.Adapter<BaseRecyclerViewAdapter.NewsViewHolder>() {

    protected var list = mutableListOf<Movies>()
        private set

    val differ: AsyncListDiffer<Movies?> = AsyncListDiffer(this, baseDiffUti())


    /*fun setList(newList: List<Movies>) {
        val diffResult = DiffUtil.calculateDiff(BaseDiffUtil(list, newList))
        list.clear()
        list.addAll(newList)
        diffResult.dispatchUpdatesTo(this)
    }*/

    fun submitList(list: List<Movies>) {
        val newList = mutableListOf<Movies>().apply{
            addAll(list)
        }
        differ.submitList(newList.toList())
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(LayoutMovieItemBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = differ.currentList.size

    inner class NewsViewHolder(private val binding: LayoutMovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val item = differ.currentList[position]
            binding.movies = item
            binding.itemClickInterface = clickListener
        }
    }



}