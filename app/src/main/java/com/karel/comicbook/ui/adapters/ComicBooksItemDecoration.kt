package com.karel.comicbook.ui.adapters

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.karel.comicbook.R

class ComicBooksItemDecoration: RecyclerView.ItemDecoration() {

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State) {

        val layoutManager = parent.layoutManager ?: return
        val adapter = parent.adapter ?: return

        val dataPosition = layoutManager.getPosition(view)
        val adapterSize = adapter.itemCount

        if (dataPosition == RecyclerView.NO_POSITION || adapterSize <= 0 || dataPosition > adapterSize - 1 ) {
            return
        }
        val type = adapter.getItemViewType(dataPosition)
        if (type == ComicBookViewHolder.type) {
            outRect.set(0, view.context.resources.getDimension(R.dimen.full_bezel).toInt(), 0, 0)
        }
    }
}