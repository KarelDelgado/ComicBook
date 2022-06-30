package com.karel.comicbook.domain.interactors

import com.karel.comicbook.data.sources.ComicBooksRemoteSource
import com.karel.comicbook.domain.exceptions.BaseException
import com.karel.comicbook.domain.model.ComicBook
import com.karel.comicbook.domain.model.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetComicBooks(private val requestBuilder: RequestBuilder,
                    private val comicBooksRemoteSource: ComicBooksRemoteSource,
                    private val ioCoroutineDispatcher: CoroutineDispatcher) {

    private var offset = 0
    private val limit = 30

    suspend fun run(): Response<List<ComicBook>> =
        withContext(ioCoroutineDispatcher) {
            try {
                val comics = comicBooksRemoteSource.get(requestBuilder.buildComicBooksRequest(offset, limit, System.currentTimeMillis().toString()))
                if (comics.isEmpty()) Response.NoData else Response.Data(comics)
            } catch (e: BaseException) {
                Response.Failure(e.error)
            }
        }
}