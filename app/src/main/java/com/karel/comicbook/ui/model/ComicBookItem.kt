package com.karel.comicbook.ui.model

import com.karel.comicbook.domain.model.ComicBook
import com.karel.comicbook.ui.adapters.IDiffCompare

data class ComicBookItem(val id: String, val title: String, val thumbnailUrl: String): IComicItem {

    companion object {
        fun build(comicBook: ComicBook): ComicBookItem {
            return ComicBookItem(comicBook.id,
                comicBook.title ?: "",
                comicBook.thumbnailUrl ?: "")
        }
    }

    override fun areItemsSame(other: IDiffCompare): Boolean {
        return other is ComicBookItem && other.id == id
    }

    override fun areContentsSame(other: IDiffCompare): Boolean {
        other as ComicBookItem
        return other.title == title &&
                other.thumbnailUrl == thumbnailUrl
    }
}
