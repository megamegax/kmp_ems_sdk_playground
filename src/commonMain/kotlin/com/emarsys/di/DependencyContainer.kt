package com.emarsys.di

import com.emarsys.EmarsysConfig
import com.emarsys.core.SdkConfig
import com.emarsys.core.SdkContext
import com.emarsys.core.state.StateMachine
import com.emarsys.setup.SetupOrganizer

fun container(): DependencyContainer {
    return DependencyInjection.container!!
}

interface DependencyContainer {
    val sdkConfig: SdkConfig
    val sdkContext: SdkContext
    val setupOrganizer: SetupOrganizer
    val stateMachine: StateMachine
}