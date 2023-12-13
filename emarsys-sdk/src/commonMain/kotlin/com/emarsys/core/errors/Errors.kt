package com.emarsys.core.errors

import com.emarsys.core.networking.HttpUrlRequest
import io.ktor.client.request.*
import io.ktor.client.statement.*

sealed class SdkException(message: String) : RuntimeException(message)

class PreconditionFailed(message: String) : SdkException(message)
class FailedRequest(response: HttpResponse) : SdkException("status: ${response.status}")
class ContactRequestFailed(response: HttpUrlRequest) : SdkException("status: ${response.url}")