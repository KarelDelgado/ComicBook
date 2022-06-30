package com.karel.comicbook.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicBookDto(val id: String,
                    val title: String?,
                    val description: String?,
                    val thumbnail: ComicBookThumbnail
)