package com.emarsys.core.session

interface SessionContext {

    var contactToken: String?
    var refreshToken: String?
    var clientState: String?

    val clientId: String?

    var deviceEventState: String?

    val additionalHeaders: Map<String, String?>
}