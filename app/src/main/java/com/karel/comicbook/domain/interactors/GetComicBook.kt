package com.karel.comicbook.domain.interactors

import com.karel.comicbook.data.sources.ComicBookRemoteSource
import com.karel.comicbook.domain.exceptions.BaseException
import com.karel.comicbook.domain.model.ComicBook
import com.karel.comicbook.domain.model.Response
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class GetComicBook(private val requestBuilder: RequestBuilder,
                   private val comicBookRemoteSource: ComicBookRemoteSource,
                   private val ioCoroutineDispatcher: CoroutineDispatcher) {

    suspend fun run(comicId: String): Response<ComicBook> =
        withContext(ioCoroutineDispatcher) {
            try {
                val request = requestBuilder.buildComicBookRequest(comicId, System.currentTimeMillis().toString())
                val comics = comicBookRemoteSource.get(request)
                if (comics.isNotEmpty()) {
                    Response.Data(comics.first())
                } else {
                    Response.NoData
                }
            } catch (e: BaseException) {
                Response.Failure(e.error)
            }
        }
}