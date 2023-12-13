package com.emarsys.core.storage

import kotlinx.browser.window
import org.w3c.dom.set

actual class IntSecureStorage : SecureStorage<Int> {
    actual override fun put(item: Int?, key: String) {
        window.localStorage[key] = item.toString()
    }

    actual override fun get(key: String): Int? {
        return window.localStorage.getItem(key)?.toInt()
    }

    actual override fun set(key: String, item: Int?) {
        window.localStorage[key] = item.toString()
    }

}