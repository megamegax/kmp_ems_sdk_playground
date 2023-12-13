package com.emarsys.core.session

import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.providers.TimestampProvider

class DefaultSessionContext(
    val timestampProvider: TimestampProvider,
    private val deviceInfoCollector: DeviceInfoCollector
) : SessionContext {


    override var contactToken: String? = null
    override var refreshToken: String? = null
    override var clientState: String? = null

    override val clientId: String? by lazy {
        deviceInfoCollector.hardwareId()
    }

    override var deviceEventState: String? = null

    override val additionalHeaders: Map<String, String?>
        get() =
            mapOf(
                "X-Client-State" to clientState,
                "X-Client-Id" to clientId,
                "X-Contact-Token" to contactToken,
                "X-Request-Order" to "${timestampProvider.provide()}"
            )
}