package com.emarsys.core.storage

import android.content.SharedPreferences

actual class IntSecureStorage(private val secureSharedPreferences: SharedPreferences) : SecureStorage<Int> {
    actual override fun put(item: Int?, key: String) {
        secureSharedPreferences.edit().putInt(key, item ?: 0).commit()
    }

    actual override fun get(key: String): Int? {
        return secureSharedPreferences.getInt(key, 0)
    }

    actual override fun set(key: String, item: Int?) {
        secureSharedPreferences.edit().putInt(key, item ?: 0).apply()
    }
}