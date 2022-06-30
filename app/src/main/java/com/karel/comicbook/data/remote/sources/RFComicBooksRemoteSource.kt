package com.karel.comicbook.data.remote.sources

import com.karel.comicbook.data.remote.api.MarvelApi
import com.karel.comicbook.domain.model.requests.ComicBooksRequest
import com.karel.comicbook.data.sources.ComicBooksRemoteSource
import com.karel.comicbook.domain.exceptions.NetworkException
import com.karel.comicbook.domain.model.ComicBook

class RFComicBooksRemoteSource(val marvelApi: MarvelApi): ComicBooksRemoteSource {
    override suspend fun get(request: ComicBooksRequest): List<ComicBook> {
        return try {
            marvelApi.getComicBooks(
                request.timestamp,
                request.userKey,
                request.hash,
                request.offset,
                request.limit).data.results.map { ComicBook.build(it) }
        } catch (e: Exception) {
            throw NetworkException(e)
        }
    }
}