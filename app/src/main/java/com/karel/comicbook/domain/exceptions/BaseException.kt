package com.karel.comicbook.domain.exceptions

abstract class BaseException: Exception() {
    abstract val error: ErrorCode
}