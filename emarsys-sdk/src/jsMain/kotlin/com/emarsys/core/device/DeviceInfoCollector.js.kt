package com.emarsys.core.device

import kotlinx.browser.window
import kotlinx.serialization.json.buildJsonObject
import org.w3c.dom.get

actual class DeviceInfoCollector {
    actual fun collect(): DeviceInfo {
        return DeviceInfo(
            "android",
            "1.0.0",
            collectBrowser(),
            "",
            "7.0",
            "1.0.0",
            "en-us",
            "+1",
            null,
            null,
            "hardwareId"
        )
    }

    actual fun deviceType(): String {
        return collectBrowser()
    }

    actual fun osVersion(): String {
        return collectBrowser()
    }

    actual fun hardwareId(): String {
        return "testHardwareId"
    }

    fun collectBrowser(): String {
        val userAgent: String = window.navigator.userAgent.lowercase()
        userAgent.indexOf("edge")
        var browser =
            when {
                userAgent.indexOf("edge") > -1 -> "edge";
                userAgent.indexOf("edg") > -1 -> "chromium based edge (dev or canary)";
                userAgent.indexOf("opr") > -1 && !!window.get("opr") -> "opera";
                userAgent.indexOf("chrome") > -1 && !!window.get("chrome") -> "chrome";
                userAgent.indexOf("trident") > -1 -> "ie";
                userAgent.indexOf("firefox") > -1 -> "firefox";
                userAgent.indexOf("safari") > -1 -> "safari";
                else -> {
                    "other"
                }
            }

        console.log(window.navigator.userAgent.toLowerCase() + "\n" + browser);
        return browser

    }

    actual fun collectDeviceInfoRequest(): String {
        val deviceInfo = collect()
        return """{
            "hwid": "${deviceInfo.hardwareId}",
            "platform": "${deviceInfo.platform}",
            "language": "${deviceInfo.language}",
            "timezone": "${deviceInfo.timezone}",
            "manufacturer": "${deviceInfo.manufacturer}",
            "model": "${deviceInfo.deviceModel}",
            "osVersion": "${deviceInfo.osVersion}",
            "displayMetrics": "${window.screen.availWidth}x${window.screen.availHeight}",
            "sdkVersion": "${deviceInfo.sdkVersion}",
            "appVersion": "${deviceInfo.applicationVersion}"
        }"""
    }
}