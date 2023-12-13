package com.emarsys.core.device

expect class DeviceInfoCollector {
    fun collect(): DeviceInfo
    fun collectDeviceInfoRequest(): String
    fun deviceType(): String
    fun osVersion(): String
    fun hardwareId(): String
}