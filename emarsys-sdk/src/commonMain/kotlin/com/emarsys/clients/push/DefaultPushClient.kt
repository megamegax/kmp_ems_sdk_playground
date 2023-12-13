package com.emarsys.clients.push

import com.emarsys.context.SdkContext
import com.emarsys.core.DefaultUrls
import com.emarsys.core.networking.HttpUrlRequest
import com.emarsys.core.networking.NetworkClient
import io.ktor.client.request.*
import io.ktor.http.*

class DefaultPushClient(
    private val networkClient: NetworkClient,
    private val sdkContext: SdkContext,
    private val defaultUrls: DefaultUrls
) : PushClient {
    override suspend fun registerPushToken(pushToken: String) {
        val url = sdkContext.createUrl(defaultUrls.clientServiceBaseUrl, path = "/client/push-token")
        val request = HttpUrlRequest(
            url.build(),
            method = HttpMethod.Put,
            body = mapOf("pushToken" to pushToken)
        )
        networkClient.send(request)
    }

    override suspend fun removePushToken() {
        val url = sdkContext.createUrl(defaultUrls.clientServiceBaseUrl, path = "/client/push-token")
        val request = HttpUrlRequest(
            url.build(),
            method = HttpMethod.Delete
        )
        networkClient.send(request)

    }
}