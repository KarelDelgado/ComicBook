package com.karel.comicbook.domain.exceptions

enum class ErrorCode {
    UNKNOWN,
    NO_CONNECTION,
    UNAUTHORIZED,
    SERVICE_UNAVAILABLE,
    RESOURCE_UNREACHABLE,
    JSON_ENCODING
}