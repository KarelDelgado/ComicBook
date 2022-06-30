package com.karel.comicbook.data.remote.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ComicsResponseDto(val code: Int,
                             val status: String,
                             val data: ComicsDataDto)
