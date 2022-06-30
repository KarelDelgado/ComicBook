package com.karel.comicbook.ui.viewmodels

import androidx.lifecycle.ViewModel
import com.karel.comicbook.domain.interactors.SaveUserKeys

class KeysViewModel(private val saveUserKeys: SaveUserKeys): ViewModel() {

    suspend fun saveKeys(privateKey: String, public: String) {
        saveUserKeys.run(privateKey, public)
    }
}