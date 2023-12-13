package com.emarsys.clients.contact

interface ContactClient {
    suspend fun linkContact(contactFieldId: Int, contactFieldValue: String? = null, openIdToken: String? = null)
    suspend fun unlinkContact()
}