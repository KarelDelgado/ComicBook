package com.karel.comicbook.data.remote.sources

import com.karel.comicbook.data.remote.api.MarvelApi
import com.karel.comicbook.domain.model.requests.ComicBookRequest
import com.karel.comicbook.data.sources.ComicBookRemoteSource
import com.karel.comicbook.domain.exceptions.NetworkException
import com.karel.comicbook.domain.model.ComicBook

class RFComicBookRemoteSource(private val marvelApi: MarvelApi): ComicBookRemoteSource {

    override suspend fun get(request: ComicBookRequest): List<ComicBook> {
        return try {
            marvelApi.getComicBook(request.comicId, request.timestamp, request.userKey, request.hash)
                .data.results.map { ComicBook.build(it) }
        } catch (e: Exception) {
            throw NetworkException(e)
        }
    }
}