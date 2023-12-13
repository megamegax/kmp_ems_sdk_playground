package com.emarsys.clients.contact.model

import kotlinx.serialization.Serializable

@Serializable
data class ContactResponse(
    val contactToken: String? = null,
    val refreshToken: String? = null
)