package com.emarsys.core

import com.emarsys.EmarsysConfig
import com.emarsys.core.state.SdkState
import com.emarsys.features.Feature

data class SdkContext(
    var sdkState: SdkState = SdkState.INACTIVE,
    var features: List<Feature> = emptyList(),
    val inAppDnd: Boolean = false,
    var config: EmarsysConfig? = null,
    val defaultUrls: DefaultUrls,
    val sdkConfig: SdkConfig
) {
    fun SdkContext.createUrl(
        baseUrl: String,
        version: String = "v3",
        withAppCode: Boolean = true,
        path: String?
    ): String {
        var url = "$baseUrl/$version"
        if (withAppCode) {
            require(config != null)
            require(config?.applicationCode != null)

            url += "/apps/${config!!.applicationCode}"
        }
        if (path != null) {
            url += path
        }
        //TODO use URL instead of String
        return url
    }

    fun update(config: EmarsysConfig? = null, sdkState: SdkState? = null, features: List<Feature>? = null) {
        if (config != null) {
            this.config = config
        }
        if (sdkState != null) {
            this.sdkState = sdkState
        }
        if (features != null) {
            this.features = features
        }
    }
}