package com.karel.comicbook.domain.exceptions

import android.util.Log
import com.squareup.moshi.JsonEncodingException
import retrofit2.HttpException
import java.net.HttpURLConnection
import java.net.UnknownHostException

class NetworkException(throwable: Throwable): BaseException() {

    override val error: ErrorCode = when(throwable) {
        is UnknownHostException -> ErrorCode.NO_CONNECTION
        is HttpException -> {
            when(throwable.code()) {
                HttpURLConnection.HTTP_CLIENT_TIMEOUT,
                HttpURLConnection.HTTP_GATEWAY_TIMEOUT,
                HttpURLConnection.HTTP_BAD_GATEWAY -> ErrorCode.RESOURCE_UNREACHABLE
                HttpURLConnection.HTTP_UNAVAILABLE -> ErrorCode.SERVICE_UNAVAILABLE
                HttpURLConnection.HTTP_UNAUTHORIZED -> ErrorCode.UNAUTHORIZED
                else -> ErrorCode.UNKNOWN
            }
        }
        is JsonEncodingException -> ErrorCode.JSON_ENCODING
        else -> ErrorCode.UNKNOWN
    }.also {
        Log.e("NetworkException", "Original message: ${throwable.message}")
    }
}