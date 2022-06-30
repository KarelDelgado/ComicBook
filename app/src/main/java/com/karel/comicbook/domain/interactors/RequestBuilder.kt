package com.karel.comicbook.domain.interactors

import com.karel.comicbook.domain.model.requests.ComicBookRequest
import com.karel.comicbook.domain.model.requests.ComicBooksRequest

class RequestBuilder(private val getUserKeys: GetUserKeys,
                     private val hashBuilder: HashBuilder) {

    suspend fun buildComicBookRequest(comicId: String, timestamp: String): ComicBookRequest {
        val userKeys = getUserKeys.run()
        val hash = hashBuilder.buildWithMD5(timestamp, userKeys)
        return ComicBookRequest(comicId, timestamp, userKeys.publicKey, hash)
    }

    suspend fun buildComicBooksRequest(offset: Int, limit: Int, timestamp: String): ComicBooksRequest {
        val userKeys = getUserKeys.run()
        val hash = hashBuilder.buildWithMD5(timestamp, userKeys)
        return ComicBooksRequest(timestamp, userKeys.publicKey, hash, offset, limit)
    }
}