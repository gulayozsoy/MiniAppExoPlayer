package com.example.miniappexoplayer.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.miniappexoplayer.databinding.LayoutMovieItemBinding
import com.example.miniappexoplayer.model.Movies
import com.example.miniappexoplayer.ui.ItemsClickListener

class DramaMovieAdapter(val clickListener: ItemsClickListener):
    RecyclerView.Adapter<DramaMovieAdapter.NewsViewHolder>(){

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

            /*if (item.posterPath != null) {
                if(item.posterPath.isNotEmpty()) {
                    val completeUrl =
                        StringBuilder().append(Constants.Constants.POSTER_URL).append(item.posterPath)
                    Glide.with( binding.moviePoster.context)
                        .load(completeUrl)
                        .into( binding.moviePoster)
                }
            }*/
            binding.itemClickInterface = clickListener
        }
    }
}