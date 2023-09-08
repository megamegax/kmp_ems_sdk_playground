package com.emarsys.core

data class SdkConfig(val version: String, val cryptoPublicKey: String) {
    companion object {
        fun load(): SdkConfig {
            return SdkConfig("0.0.0", "")
        }
    }
}