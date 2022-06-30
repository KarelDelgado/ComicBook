package com.karel.comicbook.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.karel.comicbook.domain.interactors.GetComicBooks
import com.karel.comicbook.domain.model.ComicBook
import com.karel.comicbook.domain.model.Response
import com.karel.comicbook.ui.model.ComicBookItem
import com.karel.comicbook.ui.model.UiState
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class ComicBooksViewModel(private val getComicBooks: GetComicBooks): ViewModel() {

    private val comicBooksStream = MutableSharedFlow<Response<List<ComicBook>>>()
    val uiState = comicBooksStream.map { response ->
        when(response) {
            is Response.Data<List<ComicBook>> -> {
                UiState.Data(response.value.map { ComicBookItem.build(it) })
            }
            is Response.NoData -> UiState.NoData
            is Response.Failure -> UiState.Error(response.error)
        }
    }.asLiveData()

    fun request() {
        viewModelScope.launch {
            comicBooksStream.emit(getComicBooks.run())
        }
    }
}