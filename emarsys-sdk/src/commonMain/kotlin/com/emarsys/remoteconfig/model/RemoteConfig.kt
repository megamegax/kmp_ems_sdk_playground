package com.emarsys.remoteconfig.model

import kotlinx.serialization.Serializable

@Serializable
data class RemoteConfig(
    val serviceUrls: ServiceUrls?,
    val logLevel: String?,
    val features: RemoteConfigFeatures?
)

@Serializable
data class ServiceUrls(
    var eventService: String? = null,
    var clientService: String? = null,
    var predictService: String? = null,
    var deepLinkService: String? = null,
    var inboxService: String? = null
)

@Serializable
data class RemoteConfigFeatures(
    var mobileEngage: Boolean? = null,
    var predict: Boolean? = null
)
