package com.emarsys.core.session

import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.providers.TimestampProvider

class SessionContext(val timestampProvider: TimestampProvider, private val deviceInfoCollector: DeviceInfoCollector) {


    var contactToken: String? = null
    var refreshToken: String? = null
    var clientState: String? = null

    val clientId: String? by lazy {
        deviceInfoCollector.hardwareId()
    }

    var deviceEventState: String? = null

    val additionalHeaders: Map<String, String?>
        get() =
            mapOf(
                "X-Client-State" to clientState,
                "X-Client-Id" to clientId,
                "X-Contact-Token" to contactToken,
                "X-Request-Order" to "${timestampProvider.provide()}"
            )
}