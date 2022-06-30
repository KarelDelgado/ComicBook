package com.karel.comicbook.ui.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.karel.comicbook.ui.model.ComicBookItem
import com.karel.comicbook.ui.model.EmptyItem
import com.karel.comicbook.ui.model.ErrorItem
import com.karel.comicbook.ui.model.IComicItem

private val diffItemCallback = object: DiffUtil.ItemCallback<IComicItem>() {
    override fun areItemsTheSame(oldItem: IComicItem, newItem: IComicItem): Boolean {
        return oldItem.areItemsSame(newItem)
    }

    override fun areContentsTheSame(oldItem: IComicItem, newItem: IComicItem): Boolean {
        return oldItem.areContentsSame(newItem)
    }
}

class ComicBooksListAdapter(private val comicBookClickListener: ComicBookClickListener, private val retryListener: ErrorRetryListener): ListAdapter<IComicItem, RecyclerView.ViewHolder>(diffItemCallback) {

    override fun getItemViewType(position: Int): Int {
        return when(val item = getItem(position)) {
            is ComicBookItem -> ComicBookViewHolder.type
            is EmptyItem -> EmptyItemViewHolder.type
            is ErrorItem -> ErrorItemViewHolder.type
            else -> throw IllegalStateException("Invalid item at $position: $item")
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType) {
            ComicBookViewHolder.type -> ComicBookViewHolder(parent, comicBookClickListener)
            EmptyItemViewHolder.type -> EmptyItemViewHolder(parent)
            ErrorItemViewHolder.type -> ErrorItemViewHolder(parent, retryListener)
            else -> throw IllegalArgumentException("Unknown viewType: $viewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = getItem(position)
        when(holder) {
            is ComicBookViewHolder -> holder.bind(item as ComicBookItem)
            is EmptyItemViewHolder -> holder.bind(item as EmptyItem)
            is ErrorItemViewHolder -> holder.bind(item as ErrorItem)
        }
    }
}