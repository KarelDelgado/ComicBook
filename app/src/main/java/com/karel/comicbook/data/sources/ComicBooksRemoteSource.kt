package com.karel.comicbook.data.sources

import com.karel.comicbook.domain.model.requests.ComicBooksRequest
import com.karel.comicbook.domain.model.ComicBook

interface ComicBooksRemoteSource {
    suspend fun get(request: ComicBooksRequest): List<ComicBook>
}