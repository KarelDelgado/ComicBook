package com.karel.comicbook.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.karel.comicbook.domain.interactors.GetComicBook
import com.karel.comicbook.domain.model.ComicBook
import com.karel.comicbook.domain.model.Response
import com.karel.comicbook.ui.model.ComicBookDetails
import com.karel.comicbook.ui.model.UiState
import kotlinx.coroutines.launch

class ComicBookDetailsViewModel(private val getComicBook: GetComicBook): ViewModel() {

    suspend fun requestComicDetails(comicId: String): UiState<ComicBookDetails> {
        return when(val response = getComicBook.run(comicId)) {
            is Response.Data<ComicBook> -> UiState.Data(ComicBookDetails.build(response.value))
            is Response.NoData -> UiState.NoData
            is Response.Failure -> UiState.Error(response.error)
        }
    }
}