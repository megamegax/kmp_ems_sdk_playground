package com.emarsys.clients.event

import com.emarsys.clients.event.model.EventResponse
import com.emarsys.clients.event.model.EventType

interface EventClient {
    suspend fun sendEvents(
        name: String,
        attributes: Map<String, String>?, eventType: EventType
    ): EventResponse

}