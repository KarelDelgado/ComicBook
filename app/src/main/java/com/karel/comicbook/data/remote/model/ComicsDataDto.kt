package com.karel.comicbook.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsDataDto(val limit: Int, val count: Int, val results: List<ComicBookDto>)
