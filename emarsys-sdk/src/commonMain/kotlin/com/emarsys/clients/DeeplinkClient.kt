package com.emarsys.clients

import com.emarsys.core.DefaultUrls
import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.networking.NetworkClient

class DeeplinkClient(
    val networkClient: NetworkClient,
    val defaultUrls: DefaultUrls,
    val deviceInfoCollector: DeviceInfoCollector
) {

    fun sendDeepLinkTrackingId(trackingId: String) {
//        guard let url = URL(string: defaultValues.deepLinkBaseUrl) else {
//            return //TODO: error handling what to do
//        }
//
//        let userAgent = "Emarsys SDK \(defaultValues.version) \(deviceInfoCollector.deviceType()) \(deviceInfoCollector.osVersion())"
//
//        var body = ["ems_dl": trackingId]
//        let headers = ["User-Agent": userAgent]
//
//        let request = URLRequest.create(url: url, method: .POST, headers:headers, body: body.toData())
//
//        let result: (Data, HTTPURLResponse) = try await networkClient.send(request: request)
//
//        // TODO: result?
    }

}
