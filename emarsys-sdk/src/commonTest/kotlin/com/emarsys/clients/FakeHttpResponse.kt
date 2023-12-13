package com.emarsys.clients

import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.util.*
import io.ktor.util.date.*
import io.ktor.utils.io.*
import kotlin.coroutines.CoroutineContext

class FakeHttpResponse : HttpResponse() {
    override val call: HttpClientCall
        get() = TODO("Not yet implemented")

    @InternalAPI
    override val content: ByteReadChannel
        get() = TODO("Not yet implemented")
    override val coroutineContext: CoroutineContext
        get() = TODO("Not yet implemented")
    override val headers: Headers
        get() = Headers.Empty
    override val requestTime: GMTDate
        get() = TODO("Not yet implemented")
    override val responseTime: GMTDate
        get() = TODO("Not yet implemented")
    override val status: HttpStatusCode
        get() = HttpStatusCode.Accepted
    override val version: HttpProtocolVersion
        get() = HttpProtocolVersion.HTTP_1_1
}