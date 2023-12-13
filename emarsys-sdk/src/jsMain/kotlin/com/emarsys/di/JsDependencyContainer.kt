package com.emarsys.di

import com.emarsys.context.SdkContext
import com.emarsys.core.SdkConfig
import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.storage.IntSecureStorage
import com.emarsys.core.storage.SecureStorage
import com.emarsys.core.storage.StringSecureStorage

class JsDependencyContainer : DependencyContainer, CommonDependencyContainer() {
    override val sdkConfig: SdkConfig by lazy { SdkConfig.load() }
    override val sdkContext: SdkContext by lazy { SdkContext(sdkConfig, defaultUrls) }
    override val deviceInfoCollector: DeviceInfoCollector by lazy { DeviceInfoCollector() }
    override val stringStorage: SecureStorage<String> by lazy { StringSecureStorage() }
    override val intStorage: SecureStorage<Int> by lazy { IntSecureStorage() }

}