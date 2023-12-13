package com.emarsys.setup


enum class SetupState {
    applyRemoteConfig,
    registerClient,
    registerPushToken,
    linkContact,
    appStart
}
