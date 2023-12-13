package com.emarsys.clients.event.model

import kotlinx.serialization.Serializable

@Serializable
data class EventResponse(
    val message: Map<String, String>? = null,
    val onEventAction: OnEventActionResponse? = null,
    val deviceEventState: String? = null
)
