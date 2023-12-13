package com.emarsys.clients.contact

import com.emarsys.clients.contact.model.ContactResponse
import com.emarsys.context.SdkContext
import com.emarsys.core.DefaultUrls
import com.emarsys.core.errors.ContactRequestFailed
import com.emarsys.core.errors.PreconditionFailed
import com.emarsys.core.log.LogEntry
import com.emarsys.core.log.LogLevel
import com.emarsys.core.log.SdkLogger
import com.emarsys.core.networking.HttpUrlRequest
import com.emarsys.core.networking.NetworkClient
import com.emarsys.core.session.SessionContext
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*

class DefaultContactClient(
    val emarsysClient: NetworkClient,
    val sdkContext: SdkContext,
    val sessionContext: SessionContext,
    val defaultUrls: DefaultUrls,
    val sdkLogger: SdkLogger
) : ContactClient {

    override suspend fun linkContact(
        contactFieldId: Int,
        contactFieldValue: String?,
        openIdToken: String?
    ) {
        if (contactFieldValue == null && openIdToken == null) {
            throw PreconditionFailed("Either contactFieldValue or openIdToken must not be null")
        }
        val url = sdkContext.createUrl(defaultUrls.clientServiceBaseUrl, path = "/client/contact")
        url.parameters.append("anonymous", "false")
        val request = HttpUrlRequest(
            url = url.build(),
            method = HttpMethod.Post,
            body = mapOf(
                "contactFieldId" to contactFieldId,
                "contactFieldValue" to contactFieldValue,
                "openIdToken" to openIdToken
            )
        )

        sendContactRequest(request)
    }

    override suspend fun unlinkContact() {
        val url = sdkContext.createUrl(defaultUrls.clientServiceBaseUrl, path = "/client/contact")
        url.parameters.append("anonymous", "true")

        val request = HttpUrlRequest(
            url = url.build(),
            method = HttpMethod.Post
        )

        sendContactRequest(request)
    }

    private suspend fun sendContactRequest(request: HttpUrlRequest) {
        try {
            val response = emarsysClient.send(request).body<ContactResponse>()

            sessionContext.contactToken = response.contactToken
            sessionContext.refreshToken = response.refreshToken
        } catch (e: Exception) {
            e.printStackTrace()
            sessionContext.contactToken = null
            sessionContext.refreshToken = null

            val logEntry = LogEntry(
                "default-contact-client",
                mapOf(
                    "response" to "error"
                )
            )
            sdkLogger.log(logEntry, LogLevel.DEBUG)
            throw ContactRequestFailed(request)
        }
    }

}
