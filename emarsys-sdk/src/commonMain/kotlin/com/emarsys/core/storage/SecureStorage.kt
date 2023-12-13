package com.emarsys.core.storage

interface SecureStorage<T> {

    fun put(item: T?, key: String)

    operator fun get(key: String): T?
    operator fun set(key: String, item: T?)

}
