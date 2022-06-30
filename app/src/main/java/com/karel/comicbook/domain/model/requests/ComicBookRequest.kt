package com.karel.comicbook.domain.model.requests

data class ComicBookRequest(val comicId: String,
                            val timestamp: String,
                            val userKey: String,
                            val hash: String)
