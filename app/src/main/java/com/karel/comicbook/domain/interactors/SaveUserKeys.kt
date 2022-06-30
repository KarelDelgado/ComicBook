package com.karel.comicbook.domain.interactors

import com.karel.comicbook.data.local.SharedPreferencesHelper
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class SaveUserKeys(private val sharedPreferencesHelper: SharedPreferencesHelper,
                   private val ioCoroutineDispatcher: CoroutineDispatcher) {

    suspend fun run(privateKey: String, publicKey: String) {
        withContext(ioCoroutineDispatcher) {
            sharedPreferencesHelper.put(PRIVATE_KEY, privateKey)
            sharedPreferencesHelper.put(PUBLIC_KEY, publicKey)
        }
    }
}