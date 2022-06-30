package com.karel.comicbook.ui.model

import com.karel.comicbook.domain.exceptions.ErrorCode

sealed interface UiState<out T> {
    data class Data<T>(val data: T): UiState<T>
    object NoData: UiState<Nothing>
    data class Error(val error: ErrorCode): UiState<Nothing>
}
