package com.emarsys.core.storage

expect class IntSecureStorage : SecureStorage<Int> {

    override fun put(item: Int?, key: String)

    override operator fun get(key: String): Int?
    override operator fun set(key: String, item: Int?)

}
