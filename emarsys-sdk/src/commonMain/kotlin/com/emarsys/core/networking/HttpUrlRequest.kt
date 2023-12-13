package com.emarsys.core.networking

import io.ktor.http.*
import kotlinx.serialization.Serializable

data class HttpUrlRequest(
    val url: Url,
    val method: HttpMethod,
    val body: Any? = null,
    val headers: Map<String, String?>? = null
)