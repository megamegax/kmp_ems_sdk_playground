package com.emarsys.clients.event.model

import kotlinx.serialization.Serializable

@Serializable
data class EventRequest(
    val dnd: Boolean,
    val events: List<CustomEvent>,
    val deviceEventState: String?
)
