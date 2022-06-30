package com.karel.comicbook.android

import android.app.Application
import com.karel.comicbook.di.domainModule
import com.karel.comicbook.di.presentationModule
import com.karel.comicbook.di.remoteModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

const val APP_PREFERENCES = "app_preferences"

class CBApp : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@CBApp)
            modules(domainModule, presentationModule, remoteModule)
        }
    }
}