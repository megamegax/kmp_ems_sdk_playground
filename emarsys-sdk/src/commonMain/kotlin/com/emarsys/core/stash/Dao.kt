package com.emarsys.core.stash

import com.emarsys.core.storage.Stashable

class Dao<T>(private val stash: Stash) : Stashable<T> {
    fun save(item: T) {

    }

    fun save(items: List<T>) {

    }

    fun find(predicate: () -> Boolean): List<T> {
        return emptyList()
    }
}