package com.emarsys.di

object DependencyInjection {
    var container: DependencyContainer? = null

    fun setup(dependencyContainer: DependencyContainer) {
        if (container == null) {
            container = dependencyContainer
        }
    }

    fun tearDown() {
        container = null
    }
}