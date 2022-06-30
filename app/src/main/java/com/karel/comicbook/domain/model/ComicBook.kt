package com.karel.comicbook.domain.model

import com.karel.comicbook.data.remote.model.ComicBookDto

data class ComicBook(val id: String, val title: String?, val description: String?, val thumbnailUrl: String?) {

    companion object {
        fun build(comicBookDto: ComicBookDto): ComicBook {
            return ComicBook(
                comicBookDto.id,
                comicBookDto.title,
                comicBookDto.description,
                "${comicBookDto.thumbnail.path}.${comicBookDto.thumbnail.extension}".replace("http:", "https:")
            )
        }
    }
}
