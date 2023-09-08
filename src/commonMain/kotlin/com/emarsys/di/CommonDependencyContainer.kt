package com.emarsys.di

import com.emarsys.core.SdkConfig
import com.emarsys.core.SdkContext
import com.emarsys.core.state.StateMachine
import com.emarsys.setup.SetupOrganizer

abstract class CommonDependencyContainer : DependencyContainer {

    override lateinit var sdkContext: SdkContext
    override val stateMachine: StateMachine by lazy { StateMachine(emptyList()) }
    override val setupOrganizer: SetupOrganizer by lazy { SetupOrganizer(stateMachine, sdkContext) }

}