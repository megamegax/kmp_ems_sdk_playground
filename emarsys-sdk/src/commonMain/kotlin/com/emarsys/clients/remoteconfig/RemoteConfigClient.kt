package com.emarsys.clients.remoteconfig

import com.emarsys.clients.remoteconfig.model.RemoteConfigResponse

interface RemoteConfigClient {
    suspend fun fetchRemoteConfig(): RemoteConfigResponse?

}