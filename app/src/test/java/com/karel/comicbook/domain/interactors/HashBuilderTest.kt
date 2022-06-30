package com.karel.comicbook.domain.interactors

import com.karel.comicbook.domain.model.UserKeys
import org.junit.Assert.*

import org.junit.Test

class HashBuilderTest {

    @Test
    fun test_buildWithMD5() {
        val timestamp = "1656570215"
        val userKeys = UserKeys("fake_private_key", "fake_public_key")
        val expectedHash = "6365b643242dd9f39c56c9b4374f3f55"
        assertEquals(expectedHash,  HashBuilderImpl.buildWithMD5(timestamp, userKeys))
    }
}