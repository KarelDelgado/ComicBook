package com.karel.comicbook.di

import android.content.Context
import com.karel.comicbook.android.APP_PREFERENCES
import com.karel.comicbook.data.local.SharedPreferencesHelper
import com.karel.comicbook.domain.interactors.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val domainModule = module {

    factory {
        GetComicBook(requestBuilder = get(), comicBookRemoteSource = get(), ioCoroutineDispatcher = provideIOCoroutineDispatcher())
    }

    factory {
        GetComicBooks(requestBuilder = get(), comicBooksRemoteSource = get(), ioCoroutineDispatcher = provideIOCoroutineDispatcher())
    }

    single {
        GetUserKeys(sharedPreferencesHelper = get(), ioCoroutineDispatcher = provideIOCoroutineDispatcher())
    }

    single {
        SaveUserKeys(sharedPreferencesHelper = get(), ioCoroutineDispatcher = provideIOCoroutineDispatcher())
    }

    single {
        RequestBuilder(getUserKeys = get(), hashBuilder = get())
    }

    single<HashBuilder> {
        HashBuilderImpl
    }

    single {
        SharedPreferencesHelper(androidContext().getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE))
    }
}

private fun provideIOCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.IO