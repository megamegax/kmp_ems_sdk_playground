package com.emarsys.setup.states


import com.emarsys.clients.push.PushClient
import com.emarsys.contants.Constants
import com.emarsys.core.state.State
import com.emarsys.core.storage.SecureStorage
import com.emarsys.setup.SetupState

class RegisterPushTokenState(private val pushClient: PushClient, private val secureStorage: SecureStorage<String>) :
    State {


    override var name = SetupState.registerPushToken.name

    override fun prepare() {
    }

    override suspend fun active() {
        val pushToken: String? = secureStorage[Constants.Push.pushToken]
        val lastSentPushToken: String? = secureStorage[Constants.Push.lastSentPushToken]

        if ((lastSentPushToken == null || pushToken != lastSentPushToken) && pushToken != null) {
            pushClient.registerPushToken(pushToken)
            secureStorage[Constants.Push.lastSentPushToken] = pushToken
        }
    }

    override fun relax() {
    }

}
