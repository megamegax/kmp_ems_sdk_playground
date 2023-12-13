package com.emarsys.core.networking

import com.emarsys.core.device.DeviceInfo
import kotlin.time.Duration
import kotlin.time.Duration.Companion.seconds
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.util.reflect.*

class GenericNetworkClient() : NetworkClient {
    private val client = HttpClient {
        install(ContentNegotiation) {
            json()
        }

    }

    private val retryCount = 5
    private val retryDelay: Duration = 2.seconds

    override suspend fun send(request: HttpUrlRequest): HttpResponse {
        println(request.toString())
        return when (request.method) {
            HttpMethod.Post -> client.post {
                url(request.url)
                header(HttpHeaders.ContentType, ContentType.Application.Json)
                request.headers?.forEach {
                    header(it.key, it.value)
                }
                setBody(request.body)
            }

            HttpMethod.Get -> client.get {
                url(request.url)
                request.headers?.forEach {
                    header(it.key, it.value)
                }
                request.body?.let {
                    setBody(request.body)
                }
            }

            HttpMethod.Delete -> client.delete {
                url(request.url)
                request.headers?.forEach {
                    header(it.key, it.value)
                }
                request.body?.let {
                    setBody(request.body)
                }
            }

            HttpMethod.Put -> client.put {
                url(request.url)
                request.headers?.forEach {
                    header(it.key, it.value)
                }
                request.body?.let {
                    setBody(request.body)
                }
            }

            else -> {
                throw RuntimeException("invalid method")
            }
        }
    }
}

fun HttpResponse.isOk(): Boolean {
    return status.value in 200..299
}
