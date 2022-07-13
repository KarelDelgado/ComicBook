package com.karel.comicbook.di

import com.karel.comicbook.data.remote.api.MarvelApi
import com.karel.comicbook.data.remote.api.MockInterceptor
import com.karel.comicbook.data.remote.model.ComicBookUrl1Dto
import com.karel.comicbook.data.remote.model.ComicBookUrl2Dto
import com.karel.comicbook.data.remote.model.ComicBookUrlDto
import com.karel.comicbook.data.remote.sources.RFComicBookRemoteSource
import com.karel.comicbook.data.remote.sources.RFComicBooksRemoteSource
import com.karel.comicbook.data.sources.ComicBookRemoteSource
import com.karel.comicbook.data.sources.ComicBooksRemoteSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.adapters.PolymorphicJsonAdapterFactory
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val remoteModule = module {

    single {
        buildRetrofit(MarvelApi.BASE_URL, mockInterceptor = get())
    }

    factory<MarvelApi> {
        val retrofit: Retrofit = get()
        retrofit.create(MarvelApi::class.java)
    }

    factory<ComicBookRemoteSource> {
        RFComicBookRemoteSource(marvelApi = get())
    }

    factory<ComicBooksRemoteSource> {
        RFComicBooksRemoteSource(marvelApi = get())
    }

    factory<Interceptor> {
        MockInterceptor()
    }
}

fun buildRetrofit(baseUrl: String, mockInterceptor: Interceptor): Retrofit {
    val moshi = Moshi.Builder()
        .add(ComicBookUrlDto.buildComicBookUrlAdapter())
        .addLast(KotlinJsonAdapterFactory())
        .build()
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .addInterceptor(mockInterceptor)
        .build()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}