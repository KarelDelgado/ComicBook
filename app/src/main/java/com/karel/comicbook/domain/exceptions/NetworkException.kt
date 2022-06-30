package com.karel.comicbook.domain.exceptions

import com.squareup.moshi.JsonEncodingException
import java.net.UnknownHostException

class NetworkException(throwable: Throwable): BaseException() {

    override val error: ErrorCode = when(throwable) {
        is UnknownHostException -> ErrorCode.NO_CONNECTION
        is JsonEncodingException -> ErrorCode.JSON_ENCODING
        else -> ErrorCode.UNKNOWN
    }
}