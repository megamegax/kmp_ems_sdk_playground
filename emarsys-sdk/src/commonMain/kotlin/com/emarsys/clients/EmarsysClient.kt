package com.emarsys.clients

import com.emarsys.clients.contact.model.ContactTokenResponse
import com.emarsys.context.ISdkContext
import com.emarsys.context.SdkContext
import com.emarsys.core.DefaultUrls
import com.emarsys.core.IDefaultUrls
import com.emarsys.core.networking.HttpUrlRequest
import com.emarsys.core.networking.NetworkClient
import com.emarsys.core.networking.isOk
import com.emarsys.core.session.SessionContext
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.request.request
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*

class EmarsysClient(
    private val networkClient: NetworkClient,
    private val defaultUrls: IDefaultUrls,
    private val sdkContext: ISdkContext,
    private var sessionContext: SessionContext
) : NetworkClient {

    override suspend fun send(request: HttpUrlRequest): HttpResponse {
        return refreshToken {
            val extendedRequest = extendRequest(request)
            val response: HttpResponse = networkClient.send(extendedRequest)
            handleClientState(response)
            response
        }
    }

    private fun handleClientState(response: HttpResponse) {
        if (response.isOk()) {
            val headers = response.headers.toMap().mapKeys { it.key.lowercase() }
            if (headers.containsKey("x-client-state")) {
                sessionContext.clientState = headers["x-client-state"]?.first()
            }
        }
    }

    private suspend fun refreshToken(callback: suspend () -> HttpResponse): HttpResponse {
        var requestResult = callback()
        if (requestResult.status.value == 401) {
            val contactToken = refreshContactToken()
            sessionContext.contactToken = contactToken
            requestResult = callback()
        }
        return requestResult
    }

    private suspend fun refreshContactToken(): String {
        val urlStr = sdkContext.createUrl(defaultUrls.clientServiceBaseUrl, path = "/client/contact-token")
        val refreshTokenRequest = HttpUrlRequest(
            url = urlStr.build(),
            method = HttpMethod.Post,
            body = mapOf("refreshToken" to sessionContext.refreshToken)
        )

        val extendedRefreshTokenRequest = extendRequest(refreshTokenRequest)

        val refreshResult = networkClient.send(extendedRefreshTokenRequest)
        return refreshResult.body<ContactTokenResponse>().contactToken
    }

    private fun extendRequest(originalRequest: HttpUrlRequest): HttpUrlRequest {
        val requestHeaders = originalRequest.headers?.toMap() ?: emptyMap()
        val additionalHeaders = sessionContext.additionalHeaders
        val extendedRequest = HttpUrlRequest(
            url = originalRequest.url,
            method = originalRequest.method,
            body = originalRequest.body,
            headers = requestHeaders + additionalHeaders
        )


        return extendedRequest
    }
}
