package com.emarsys.clients.device

import com.emarsys.clients.EmarsysClient
import com.emarsys.context.SdkContext
import com.emarsys.core.DefaultUrls
import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.networking.HttpUrlRequest
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*

class DefaultDeviceClient(
    private val emarsysClient: EmarsysClient,
    private val sdkContext: SdkContext,
    private val defaultUrls: DefaultUrls,
    private val deviceInfoCollector: DeviceInfoCollector
) : DeviceClient {
    override suspend fun registerClient() {
        val deviceInfoRequestBody = deviceInfoCollector.collectDeviceInfoRequest()
        println(deviceInfoRequestBody)
        val url = sdkContext.createUrl(defaultUrls.clientServiceBaseUrl, path = "/client")
        val request = HttpUrlRequest(
            url = url.build(),
            method = HttpMethod.Post,
            body = deviceInfoRequestBody
        )
        val response = emarsysClient.send(request)
        println(response.status)
    }

}