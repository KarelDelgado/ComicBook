package com.karel.comicbook.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.karel.comicbook.R
import com.karel.comicbook.databinding.ItemErrorStateBinding
import com.karel.comicbook.ui.model.ErrorItem

class ErrorItemViewHolder(parent: ViewGroup, private val retryListener: ErrorRetryListener): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context)
    .inflate(R.layout.item_error_state, parent, false)) {

    companion object {
        const val type = R.layout.item_error_state
    }

    private val binding = ItemErrorStateBinding.bind(itemView)

    init {
        binding.retryBtn.setOnClickListener {
            retryListener.onRetry()
        }
    }

    fun bind(model: ErrorItem) {
        binding.errorStateMessage.text = model.message
    }
}