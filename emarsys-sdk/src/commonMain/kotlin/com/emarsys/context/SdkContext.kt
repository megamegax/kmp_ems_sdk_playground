package com.emarsys.context

import com.emarsys.EmarsysConfig
import com.emarsys.core.DefaultUrls
import com.emarsys.core.SdkConfig
import com.emarsys.core.state.SdkState
import com.emarsys.features.Feature
import io.ktor.http.*

data class SdkContext(
    val sdkConfig: SdkConfig,
    var defaultUrls: DefaultUrls,
    var sdkState: SdkState = SdkState.INACTIVE,
    var features: MutableList<Feature> = mutableListOf(),
    val inAppDnd: Boolean = false,
    var config: EmarsysConfig? = null
) {
    fun createUrl(
        baseUrl: String,
        version: String = "v3",
        withAppCode: Boolean = true,
        path: String?
    ): URLBuilder {
        var url = "$baseUrl/$version"
        if (withAppCode) {
            require(config != null)
            require(config?.applicationCode != null)

            url += "/apps/${config!!.applicationCode}"
        }
        if (path != null) {
            url += path
        }
        return URLBuilder(url)
    }

    fun update(
        config: EmarsysConfig? = null,
        sdkState: SdkState? = null,
        features: List<Feature>? = null,
        defaultUrls: DefaultUrls? = null
    ) {
        if (config != null) {
            this.config = config
        }
        if (sdkState != null) {
            this.sdkState = sdkState
        }
        if (features != null) {
            this.features = features.toMutableList()
        }
        if (defaultUrls != null) {
            this.defaultUrls = defaultUrls
        }
    }
}