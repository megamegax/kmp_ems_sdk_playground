package com.emarsys.clients.event

import com.emarsys.clients.event.model.CustomEvent
import com.emarsys.clients.event.model.EventRequest
import com.emarsys.clients.event.model.EventResponse
import com.emarsys.clients.event.model.EventType
import com.emarsys.context.SdkContext
import com.emarsys.core.DefaultUrls
import com.emarsys.core.networking.HttpUrlRequest
import com.emarsys.core.networking.NetworkClient
import com.emarsys.core.providers.TimestampProvider
import com.emarsys.core.session.SessionContext
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.date.*
import kotlinx.datetime.Clock

class DefaultEventClient(
    private val networkClient: NetworkClient,
    private val sdkContext: SdkContext,
    private val defaultUrls: DefaultUrls,
    private val sessionContext: SessionContext,
    private val timestampProvider: TimestampProvider
) : EventClient {
    override suspend fun sendEvents(
        name: String,
        attributes: Map<String, String>?,
        eventType: EventType
    ): EventResponse {
        val url = sdkContext.createUrl(defaultUrls.eventServiceBaseUrl, version = "v4", path = "/client/events")
        val eventRequest = EventRequest(
            dnd = sdkContext.inAppDnd,
            events = listOf(
                CustomEvent(
                    type = eventType.name.lowercase(),
                    name = name,
                    attributes = attributes,
                    timestamp = Clock.System.now().toString()
                )
            ),
            deviceEventState = sessionContext.deviceEventState
        )

        val request = HttpUrlRequest(
            url = url.build(),
            method = HttpMethod.Post,
            body = eventRequest
        )
        val response = networkClient.send(request)
        println(response.status)

        val parsedResponse = response.body<EventResponse>()

        sessionContext.deviceEventState = parsedResponse.deviceEventState

        return parsedResponse
    }
}