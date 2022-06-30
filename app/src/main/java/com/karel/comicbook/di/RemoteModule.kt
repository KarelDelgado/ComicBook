package com.karel.comicbook.di

import com.karel.comicbook.data.remote.api.MarvelApi
import com.karel.comicbook.data.remote.sources.RFComicBookRemoteSource
import com.karel.comicbook.data.remote.sources.RFComicBooksRemoteSource
import com.karel.comicbook.data.sources.ComicBookRemoteSource
import com.karel.comicbook.data.sources.ComicBooksRemoteSource
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val remoteModule = module {

    single {
        buildRetrofit(MarvelApi.BASE_URL)
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
}

fun buildRetrofit(baseUrl: String): Retrofit {
    val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()
    val okHttpClient = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        })
        .build()
    return Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .client(okHttpClient)
        .build()
}

//fun buildStocksApi(retrofit: Retrofit): MarvelApi = retrofit.create(StocksApi::class.java)