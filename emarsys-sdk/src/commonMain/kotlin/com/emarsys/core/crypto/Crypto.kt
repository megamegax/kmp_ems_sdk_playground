package com.emarsys.core.crypto

interface Crypto {
    fun verify(content: String, signature: String): Boolean

}