package com.karel.comicbook.domain.interactors

import com.karel.comicbook.domain.model.UserKeys
import java.math.BigInteger
import java.security.MessageDigest

interface HashBuilder {
    fun buildWithMD5(timestamp: String, userKeys: UserKeys): String
}

object HashBuilderImpl : HashBuilder {
    override fun buildWithMD5(timestamp: String, userKeys: UserKeys): String {
        val input = "$timestamp${userKeys.privateKey}${userKeys.publicKey}"
        val md = MessageDigest.getInstance("MD5")
        return BigInteger(1, md.digest(input.toByteArray())).toString(16).padStart(32, '0')
    }
}