package com.emarsys.core.storage

import android.content.SharedPreferences

actual class StringSecureStorage(private val secureSharedPreferences: SharedPreferences) : SecureStorage<String> {
    actual override fun put(item: String?, key: String) {
        secureSharedPreferences.edit().putString(key, item).commit()
    }

    actual override fun get(key: String): String? {
        return secureSharedPreferences.getString(key, null)
    }

    actual override fun set(key: String, item: String?) {
        secureSharedPreferences.edit().putString(key, item).apply()
    }
}