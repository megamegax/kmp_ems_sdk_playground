package com.emarsys.di

import com.emarsys.api.contact.ContactApi
import com.emarsys.clients.contact.ContactClient
import com.emarsys.clients.device.DeviceClient
import com.emarsys.clients.event.EventClient
import com.emarsys.clients.push.PushClient
import com.emarsys.clients.remoteconfig.RemoteConfigClient
import com.emarsys.core.DefaultUrls
import com.emarsys.core.SdkConfig
import com.emarsys.context.SdkContext
import com.emarsys.core.crypto.Crypto
import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.log.SdkLogger
import com.emarsys.core.networking.NetworkClient
import com.emarsys.core.providers.DoubleProvider
import com.emarsys.core.providers.TimestampProvider
import com.emarsys.core.providers.UuidProvider
import com.emarsys.core.session.SessionContext
import com.emarsys.core.state.StateMachine
import com.emarsys.core.storage.SecureStorage
import com.emarsys.remoteconfig.RemoteConfigHandler
import com.emarsys.setup.SetupOrganizer

fun container(): DependencyContainer {
    return DependencyInjection.container!!
}

interface DependencyContainer {
    val sdkConfig: SdkConfig
    val defaultUrls: DefaultUrls
    val sdkContext: SdkContext
    val sessionContext: SessionContext

    val contactApi: ContactApi

    //  val eventApi: EventApi
    val pushClient: PushClient
    val contactClient: ContactClient
    val emarsysClient: NetworkClient

    val eventClient: EventClient
    val remoteConfigClient: RemoteConfigClient
    val genericNetworkClient: NetworkClient

    val deviceClient: DeviceClient
    val crypto: Crypto
    val sdkLogger: SdkLogger

    val stringStorage: SecureStorage<String>
    val intStorage: SecureStorage<Int>
    val uuidProvider: UuidProvider
    val timestampProvider: TimestampProvider
    val doubleProvider: DoubleProvider

    val deviceInfoCollector: DeviceInfoCollector

    val setupOrganizer: SetupOrganizer
    val stateMachine: StateMachine
    val remoteConfigHandler: RemoteConfigHandler
}