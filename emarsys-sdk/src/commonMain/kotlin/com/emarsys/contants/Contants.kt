package com.emarsys.contants

object Constants {

    object AppStart {
        const val appStartEventName = "app:start"
    }

    object Push {
        const val pushToken = "pushToken"
        const val lastSentPushToken = "lastSentPushToken"
    }

    object Contact {
        const val contactToken = "contactToken"
        const val contactFieldId = "contactFieldId"
        const val contactFieldValue = "contactFieldValue"
        const val openIdToken = "openIdToken"
    }

    object Logger {
        const val category = "EmarsysSDK"
        const val subsystem = "com.emarsys"
        const val maxColumns = 8
    }

    object ActionTypes {
        const val customEvent = "MECustomEvent"
        const val appEvent = "MEAppEvent"
        const val openExternalURL = "OpenExternalUrl"
        const val buttonClicked = "ButtonClicked"
        const val dismiss = "Dismiss"
        const val requestPushPermission = "RequestPushPermission"
        const val badgeCount = "BadgeCount"
        const val copyToClipboard = "CopyToClipboard"
    }
}