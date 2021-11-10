package com.example.miniappexoplayer.base

import android.annotation.SuppressLint
import androidx.recyclerview.widget.DiffUtil
import com.example.miniappexoplayer.model.Movies


//If AsyncDiff not used below code will be used
/*class BaseDiffUtil(
    private val oldList: List<Movies>,
    private val newList: List<Movies>
): DiffUtil.Callback() {

    override fun getOldListSize() = oldList.size

    override fun getNewListSize() = newList.size

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}*/

fun baseDiffUti(): DiffUtil.ItemCallback<Movies> {
    return object : DiffUtil.ItemCallback<Movies>() {
            override fun areItemsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return oldItem.id == newItem.id
            }

            @SuppressLint("DiffUtilEquals")
            override fun areContentsTheSame(oldItem: Movies, newItem: Movies): Boolean {
                return (oldItem == newItem)
            }

    }
}

