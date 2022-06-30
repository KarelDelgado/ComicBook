package com.karel.comicbook.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicBookThumbnail(val path: String, val extension: String)
