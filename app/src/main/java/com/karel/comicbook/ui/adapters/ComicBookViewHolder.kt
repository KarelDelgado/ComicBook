package com.karel.comicbook.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.karel.comicbook.R
import com.karel.comicbook.databinding.ItemComicBookBinding
import com.karel.comicbook.ui.model.ComicBookItem

class ComicBookViewHolder(parent: ViewGroup, private val comicBookClickListener: ComicBookClickListener): RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
    .inflate(R.layout.item_comic_book, parent, false)) {

    companion object {
        const val type = R.layout.item_comic_book
    }

    private var comicItem: ComicBookItem? = null
    private val binding = ItemComicBookBinding.bind(itemView)

    init {
        itemView.setOnClickListener {
            comicItem?.let {
                comicBookClickListener.onClick(it.id)
            }
        }
    }

    fun bind(model: ComicBookItem) {
        comicItem = model
        binding.comicBookTitle.text = model.title
        Glide.with(itemView.context)
            .load(model.thumbnailUrl)
            .placeholder(R.mipmap.placeholder_bg)
            .into(binding.comicBookThumbnail)
    }
}