package com.emarsys.api

interface ActivationAware {
    suspend fun activated() {}

}