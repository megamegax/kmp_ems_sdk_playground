package com.emarsys.setup.states

import com.emarsys.clients.device.DeviceClient
import com.emarsys.core.state.State
import com.emarsys.setup.SetupState

class RegisterClientState(private val deviceClient: DeviceClient) : State {


    override var name = SetupState.registerClient.name

    override fun prepare() {
    }

    override suspend fun active() {
        deviceClient.registerClient()
    }

    override fun relax() {
    }

}
