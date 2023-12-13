package com.emarsys.clients.event.model

import kotlinx.serialization.Serializable

@Serializable
data class CustomEvent(
    val type: String,
    val name: String,
    val attributes: Map<String, String>?,
    val timestamp: String
) {

}


enum class EventType {
    INTERNAL,
    CUSTOM
}
