package com.karel.comicbook.ui.model

import com.karel.comicbook.domain.model.ComicBook

data class ComicBookDetails(val id: String, val title: String, val description: String, val thumbnailUrl: String) {

    companion object {
        fun build(comicBook: ComicBook): ComicBookDetails {
            return ComicBookDetails(
                comicBook.id,
                comicBook.title ?: "",
                comicBook.description ?: "",
                comicBook.thumbnailUrl ?: "")
        }
    }
}
