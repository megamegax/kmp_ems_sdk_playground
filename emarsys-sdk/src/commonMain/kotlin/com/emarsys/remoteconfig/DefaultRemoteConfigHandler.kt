package com.emarsys.remoteconfig

import com.emarsys.clients.remoteconfig.RemoteConfigClient
import com.emarsys.clients.remoteconfig.model.LuckyLogger
import com.emarsys.context.SdkContext
import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.log.SdkLogger
import com.emarsys.core.providers.DoubleProvider
import com.emarsys.features.Feature
import com.emarsys.remoteconfig.model.RemoteConfigFeatures
import com.emarsys.remoteconfig.model.ServiceUrls


class DefaultRemoteConfigHandler(
    private val deviceInfoCollector: DeviceInfoCollector,
    private val remoteConfigClient: RemoteConfigClient,
    private val sdkContext: SdkContext,
    private val sdkLogger: SdkLogger,
    private val doubleProvider: DoubleProvider
) : RemoteConfigHandler {


    override suspend fun handle() {
        val config = remoteConfigClient.fetchRemoteConfig()
        sdkLogger.trace("REMOTE CONFIG IS DOWNLOADED: $config")
        val hardwareId = deviceInfoCollector.collect().hardwareId
        if (config != null) {
            applyServiceUrls(config.serviceUrls)
            applyLogLevel(config.logLevel)
            applyFeatures(config.features)
            applyLuckyLogger(config.luckyLogger)

            val hardwareIdOverrides = config.overrides

            hardwareIdOverrides?.get(hardwareId)?.let {
                applyServiceUrls(it.serviceUrls)
                applyFeatures(it.features)
                applyLogLevel(it.logLevel)
            }
        }
    }

    private fun applyFeatures(features: RemoteConfigFeatures?) {
        features?.let {
            it.mobileEngage?.let { mobileEngageFeature ->
                if (mobileEngageFeature) {
                    if (!sdkContext.features.contains(Feature.MOBILE_ENGAGE)) {
                        sdkContext.features.add(Feature.MOBILE_ENGAGE)
                    } else {
                        if (sdkContext.features.contains(Feature.MOBILE_ENGAGE)) {
                            sdkContext.features.removeAll { feature ->
                                feature == Feature.MOBILE_ENGAGE
                            }
                        }
                    }
                }
            }
            it.predict?.let { predictFeature ->
                if (predictFeature) {
                    if (!sdkContext.features.contains(Feature.PREDICT)) {
                        sdkContext.features.add(Feature.PREDICT)
                    }
                } else {
                    if (sdkContext.features.contains(Feature.PREDICT)) {
                        sdkContext.features.removeAll { feature ->
                            feature == Feature.PREDICT
                        }
                    }
                }
            }
        }
    }

    private fun applyServiceUrls(serviceUrls: ServiceUrls?) {
        serviceUrls?.let { override ->
            sdkContext.update(
                defaultUrls = sdkContext.defaultUrls.copy(
                    clientServiceBaseUrl = override.clientService ?: sdkContext.defaultUrls.clientServiceBaseUrl,
                    eventServiceBaseUrl = override.eventService ?: sdkContext.defaultUrls.eventServiceBaseUrl,
                    predictBaseUrl = override.predictService ?: sdkContext.defaultUrls.predictBaseUrl,
                    deepLinkBaseUrl = override.deepLinkService ?: sdkContext.defaultUrls.deepLinkBaseUrl,
                    inboxBaseUrl = override.inboxService ?: sdkContext.defaultUrls.inboxBaseUrl
                )
            )
        }
    }

    private fun applyLogLevel(logLevel: String?) {
        logLevel?.let { override ->
            sdkContext.sdkConfig.remoteLogLevel = override
        }
    }

    private fun applyLuckyLogger(luckyLogger: LuckyLogger?) {
        luckyLogger?.let { override ->
            val randomNumber = doubleProvider.provide()
            if (override.threshold != 0.0 && randomNumber <= override.threshold) {
                sdkContext.sdkConfig.remoteLogLevel = override.logLevel
            }
        }
    }
}
