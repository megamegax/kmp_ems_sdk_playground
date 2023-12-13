package com.emarsys.core.log

import com.emarsys.contants.Constants
import com.emarsys.core.DefaultUrls
import io.github.oshai.kotlinlogging.KotlinLogging


class SdkLogger(
    private val defaultUrls: DefaultUrls
) {

    fun trace(logMessage: String, topic: String = "EmarsysSDK") {
        logToConsole(LogEntry(topic, mapOf("message" to logMessage)), LogLevel.ERROR)
    }

    fun log(logEntry: LogEntry, level: LogLevel) {
        logToConsole(logEntry, level)

        val url: String? = logEntry.data["url"] as String?
        if (!(logEntry.topic == "log_request" && url != null && url == defaultUrls.loggingUrl) && level.name >= LogLevel.ERROR.name || logEntry.topic == "app:start") {
            sendRemoteLog()
        }
    }

    private fun logToConsole(logEntry: LogEntry, level: LogLevel) {
        if (logEntry.hideSensitiveData) {

            KotlinLogging.logger {}.info {
                "${Constants.Logger.category}"
            }
        } else {
            KotlinLogging.logger {}.info {
                "${Constants.Logger.category}"
            }
        }
    }

    private fun sendRemoteLog() {
        //TODO send log to logDealer
    }
}
