package com.karel.comicbook.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karel.comicbook.R
import com.karel.comicbook.databinding.ItemEmptyStateBinding
import com.karel.comicbook.ui.model.EmptyItem

class EmptyItemViewHolder(parent: ViewGroup): RecyclerView.ViewHolder(LayoutInflater.from(parent.context)
    .inflate(R.layout.item_empty_state, parent, false)) {

    companion object {
        const val type = R.layout.item_empty_state
    }

    private val binding = ItemEmptyStateBinding.bind(itemView)

    fun bind(model: EmptyItem) {
        binding.emptyStateMessage.text = model.message
    }
}