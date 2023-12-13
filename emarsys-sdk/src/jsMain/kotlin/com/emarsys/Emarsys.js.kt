package com.emarsys

import com.emarsys.di.DependencyInjection
import com.emarsys.di.JsDependencyContainer
import com.emarsys.di.container

actual object Emarsys {
    actual suspend fun initialize() {
        DependencyInjection.setup(JsDependencyContainer())
    }

    actual suspend fun enableTracking(config: EmarsysConfig) {
        container().sdkContext.update(config = config)
        container().setupOrganizer.setup()
    }

}