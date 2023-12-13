package com.emarsys.core.networking

import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*

interface NetworkClient {

    suspend fun send(request: HttpUrlRequest): HttpResponse

}
