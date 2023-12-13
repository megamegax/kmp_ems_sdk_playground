package com.emarsys.core.storage

import kotlinx.browser.window

actual class StringSecureStorage : SecureStorage<String> {
    actual override fun put(item: String?, key: String) {
        window.localStorage.setItem(key, item ?: "")
    }

    actual override fun get(key: String): String? {
        return window.localStorage.getItem(key)
    }

    actual override fun set(key: String, item: String?) {
        window.localStorage.setItem(key, item ?: "")
    }

}