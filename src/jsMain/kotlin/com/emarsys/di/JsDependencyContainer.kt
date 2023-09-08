package com.emarsys.di

import com.emarsys.core.SdkConfig
import com.emarsys.di.DependencyContainer

class JsDependencyContainer : DependencyContainer, CommonDependencyContainer() {
    override val sdkConfig: SdkConfig by lazy { SdkConfig.load() }
}