package com.emarsys.setup.states

import com.emarsys.clients.event.EventClient
import com.emarsys.clients.event.model.EventType
import com.emarsys.contants.Constants
import com.emarsys.core.state.State
import com.emarsys.setup.SetupState

class AppStartState(private val eventClient: EventClient) : State {


    override var name = SetupState.appStart.name

    override fun prepare() {
    }

    override suspend fun active() {
        eventClient.sendEvents(Constants.AppStart.appStartEventName, null, EventType.INTERNAL)
    }

    override fun relax() {
    }
}
