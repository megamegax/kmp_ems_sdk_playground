package com.emarsys.di

import com.emarsys.core.SdkConfig
import com.emarsys.di.DependencyContainer

class AndroidDependencyContainer : DependencyContainer, CommonDependencyContainer() {
    override val sdkConfig: SdkConfig by lazy { SdkConfig.load() }
}