package com.karel.comicbook.data.sources

import com.karel.comicbook.domain.model.requests.ComicBookRequest
import com.karel.comicbook.domain.model.ComicBook

interface ComicBookRemoteSource {
    suspend fun get(request: ComicBookRequest): List<ComicBook>
}