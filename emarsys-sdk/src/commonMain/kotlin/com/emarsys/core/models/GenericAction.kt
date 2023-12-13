package com.emarsys.core.models

import com.emarsys.core.errors.PreconditionFailed
import io.ktor.http.*
import kotlinx.serialization.Serializable

@Serializable
data class GenericAction(
    val type: String,
    val url: String?,
    val name: String?,
    val payload: Map<String, String>?,
    val method: String?,
    val value: Int?,
    val text: String?
) {
    fun getSafeName(): String {
        if (name == null) {
            throw PreconditionFailed("Action name must not be null")
        }
        return name
    }

    fun getSafeURL(): Url {
        if (url == null) {
            throw throw PreconditionFailed("Action URL must not be null")
        } else {
            try {
                return URLBuilder(url).build()
            } catch (e: Exception) {
                throw PreconditionFailed("Action URL must be valid")
            }
        }

    }

    fun getSafeMethod(): String {
        if (method == null) {
            throw PreconditionFailed("Action method must not be null")
        }
        return method
    }

    fun getSafeValue(): Int {
        if (value == null) {
            throw PreconditionFailed("Action value must not be null")
        }
        return value
    }

    fun getSafeText(): String {
        if (text == null) {
            throw PreconditionFailed("Action text must not be null")
        }
        return text
    }
}
