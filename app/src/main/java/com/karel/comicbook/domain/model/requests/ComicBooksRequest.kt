package com.karel.comicbook.domain.model.requests

data class ComicBooksRequest(val timestamp: String,
                             val userKey: String,
                             val hash: String,
                             val offset: Int,
                             val limit: Int)
