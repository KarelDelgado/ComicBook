package com.karel.comicbook.data.remote.model

import com.squareup.moshi.JsonClass
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory

@JsonClass(generateAdapter = true)
sealed class ComicBookUrlDto(open val type: String, val url: String) {
    companion object {
        fun buildComicBookUrlAdapter(): PolymorphicJsonAdapterFactory<ComicBookUrlDto> =
            PolymorphicJsonAdapterFactory.of(ComicBookUrlDto::class.java, "type")
                .withSubtype(ComicBookUrl1Dto::class.java, "detail")
                .withSubtype(ComicBookUrl2Dto::class.java, "gallery")
    }
}

@JsonClass(generateAdapter = true)
class ComicBookUrl1Dto(type: String,
                       url: String,
                       val count: Int): ComicBookUrlDto(type, url)
@JsonClass(generateAdapter = true)
class ComicBookUrl2Dto(type: String,
                       url: String,
                       val short: Boolean): ComicBookUrlDto(type, url)