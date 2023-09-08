package com.emarsys

import com.emarsys.di.DependencyInjection

expect object Emarsys {
    suspend fun initialize()

    suspend fun enableTracking(config: EmarsysConfig)

}