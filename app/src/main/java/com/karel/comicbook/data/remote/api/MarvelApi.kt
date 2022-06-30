package com.karel.comicbook.data.remote.api

import com.karel.comicbook.data.remote.model.ComicsResponseDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MarvelApi {

    companion object {
        const val BASE_URL = "https://gateway.marvel.com"
        const val COMIC_BOOK_ENDPOINT = "/v1/public/comics/{comicId}"
        const val COMIC_BOOKS_ENDPOINT = "/v1/public/comics"
    }

    @GET(COMIC_BOOK_ENDPOINT)
    suspend fun getComicBook(@Path("comicId") comicId: String,
                             @Query("ts") timestamp: String,
                             @Query("apikey") publicKey: String,
                             @Query("hash") hash: String): ComicsResponseDto

    @GET(COMIC_BOOKS_ENDPOINT)
    suspend fun getComicBooks(@Query("ts") timestamp: String,
                             @Query("apikey") publicKey: String,
                             @Query("hash") hash: String,
                              @Query("offset") offset: Int,
                              @Query("limit") limit: Int): ComicsResponseDto
}