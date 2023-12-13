package com.emarsys.core.crypto

class DefaultCrypto : Crypto {
    override fun verify(content: String, signature: String): Boolean {
        return true
    }
}