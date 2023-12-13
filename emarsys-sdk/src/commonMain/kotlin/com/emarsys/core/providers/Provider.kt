package com.emarsys.core.providers

interface Provider<T> {

    fun provide(): T
}