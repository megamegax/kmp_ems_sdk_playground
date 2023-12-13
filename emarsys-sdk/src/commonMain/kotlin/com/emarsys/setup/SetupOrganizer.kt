package com.emarsys.setup

import com.emarsys.context.SdkContext
import com.emarsys.core.log.SdkLogger
import com.emarsys.core.state.SdkState
import com.emarsys.core.state.StateMachine

class SetupOrganizer(
    private val stateMachine: StateMachine,
    private val sdkContext: SdkContext,
    private val logger: SdkLogger
) {
    suspend fun setup() {
        logger.trace("setup")
        stateMachine.activate()
        stateMachine.stateLifecycle.collect {
            sdkContext.sdkState = SdkState.ACTIVE
        }
    }
}