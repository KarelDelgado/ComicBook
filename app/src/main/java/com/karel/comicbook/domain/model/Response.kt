package com.karel.comicbook.domain.model

import com.karel.comicbook.domain.exceptions.ErrorCode

sealed interface Response<out T> {
    data class Data<T>(val value: T): Response<T>
    object NoData: Response<Nothing>
    data class Failure(val error: ErrorCode): Response<Nothing>
}
