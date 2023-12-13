package com.emarsys.clients.remoteconfig

import com.emarsys.clients.remoteconfig.model.RemoteConfigResponse

class DefaultRemoteConfigClient : RemoteConfigClient {
    override suspend fun fetchRemoteConfig(): RemoteConfigResponse? {
        return null
    }
}