package com.emarsys.api.contact

import com.emarsys.core.log.LogEntry
import com.emarsys.core.log.LogLevel
import com.emarsys.core.log.SdkLogger

class LoggingContact(private val logger: SdkLogger) : ContactInstance {

    override suspend fun linkContact(contactFieldId: Int, contactFieldValue: String) {
        val entry = LogEntry.createMethodNotAllowedEntry(
            this,
            params = mapOf(
                "contactFieldId" to contactFieldId,
                "contactFieldValue" to contactFieldValue
            )
        )
        logger.log(entry, LogLevel.DEBUG)
    }

    override suspend fun linkAuthenticatedContact(contactFieldId: Int, openIdToken: String) {
        val entry = LogEntry.createMethodNotAllowedEntry(
            this,
            params = mapOf(
                "contactFieldId" to contactFieldId,
                "openIdToken" to openIdToken
            )
        )
        logger.log(entry, LogLevel.DEBUG)
    }

    override suspend fun unlinkContact() {
        val entry = LogEntry.createMethodNotAllowedEntry(this)
        logger.log(entry, LogLevel.DEBUG)
    }
}