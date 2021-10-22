package com.example.miniappexoplayer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniappexoplayer.databinding.LayoutMovieItemBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.ItemsClickListener

class ActionMovieAdapter(val clickListener: ItemsClickListener):
    RecyclerView.Adapter<ActionMovieAdapter.NewsViewHolder>(){

    private var list: MutableList<Movies> = mutableListOf()

    fun updateItems(items: List<Movies>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return NewsViewHolder(LayoutMovieItemBinding.inflate(layoutInflater, parent, false))
    }


    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int = list.size

    inner class NewsViewHolder(private val binding: LayoutMovieItemBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(position: Int) {
            val item = list[position]
            binding.movies = item
            binding.itemClickInterface = clickListener
        }
    }
}