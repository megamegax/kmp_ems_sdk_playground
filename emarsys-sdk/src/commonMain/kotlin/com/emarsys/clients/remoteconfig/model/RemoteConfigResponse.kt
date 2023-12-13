package com.emarsys.clients.remoteconfig.model

import com.emarsys.remoteconfig.model.RemoteConfig
import com.emarsys.remoteconfig.model.RemoteConfigFeatures
import com.emarsys.remoteconfig.model.ServiceUrls
import kotlinx.serialization.Serializable

@Serializable
data class RemoteConfigResponse(
    val serviceUrls: ServiceUrls?,
    val logLevel: String?,
    val luckyLogger: LuckyLogger?,
    val features: RemoteConfigFeatures?,
    var overrides: Map<String, RemoteConfig>? = null
)

@Serializable
data class LuckyLogger(
    val logLevel: String,
    val threshold: Double
)
