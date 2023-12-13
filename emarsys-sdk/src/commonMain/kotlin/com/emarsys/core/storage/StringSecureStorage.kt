package com.emarsys.core.storage

expect class StringSecureStorage : SecureStorage<String> {

    override fun put(item: String?, key: String)

    override operator fun get(key: String): String?
    override operator fun set(key: String, item: String?)

}
