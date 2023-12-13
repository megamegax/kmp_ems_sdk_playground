package com.emarsys.setup.states

import com.emarsys.core.state.State
import com.emarsys.remoteconfig.RemoteConfigHandler
import com.emarsys.setup.SetupState

class ApplyRemoteConfigState(
    private val remoteConfigHandler: RemoteConfigHandler
) : State {


    override var name = SetupState.applyRemoteConfig.name

    override fun prepare() {
    }

    override suspend fun active() {
        remoteConfigHandler.handle()
    }

    override fun relax() {
    }

}
