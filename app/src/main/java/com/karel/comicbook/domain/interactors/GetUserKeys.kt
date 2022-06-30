package com.karel.comicbook.domain.interactors

import com.karel.comicbook.data.local.SharedPreferencesHelper
import com.karel.comicbook.domain.model.UserKeys
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

const val PRIVATE_KEY = "private_key"
const val PUBLIC_KEY = "public_key"

class GetUserKeys(private val sharedPreferencesHelper: SharedPreferencesHelper,
                  private val ioCoroutineDispatcher: CoroutineDispatcher) {

    suspend fun run(): UserKeys {
        return withContext(ioCoroutineDispatcher) {
            UserKeys(sharedPreferencesHelper[PRIVATE_KEY, ""] ?: "",
                sharedPreferencesHelper[PUBLIC_KEY, ""] ?: "")
        }
    }
}