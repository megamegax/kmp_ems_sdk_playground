package com.emarsys

import com.emarsys.di.AndroidDependencyContainer
import com.emarsys.di.DependencyInjection
import com.emarsys.di.container
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.withContext

actual object Emarsys {

    actual suspend fun initialize() {
        DependencyInjection.setup(AndroidDependencyContainer())
    }

    actual suspend fun enableTracking(config: EmarsysConfig) {
        config.isValid()
        container().sdkContext.update(config = config)
        container().setupOrganizer.setup()
    }

}