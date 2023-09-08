package com.emarsys.setup

import com.emarsys.core.SdkContext
import com.emarsys.core.state.SdkState
import com.emarsys.core.state.StateMachine

class SetupOrganizer(
    private val stateMachine: StateMachine,
    private val sdkContext: SdkContext
) {
    suspend fun setup() {
        stateMachine.activate()
        stateMachine.stateLifecycle.collect {
            sdkContext.sdkState = SdkState.ACTIVE
        }
    }
}