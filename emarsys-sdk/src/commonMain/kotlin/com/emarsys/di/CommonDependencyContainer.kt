package com.emarsys.di

import com.emarsys.api.contact.Contact
import com.emarsys.api.contact.ContactApi
import com.emarsys.api.contact.LoggingContact
import com.emarsys.clients.EmarsysClient
import com.emarsys.clients.contact.ContactClient
import com.emarsys.clients.contact.DefaultContactClient
import com.emarsys.clients.device.DefaultDeviceClient
import com.emarsys.clients.device.DeviceClient
import com.emarsys.clients.event.DefaultEventClient
import com.emarsys.clients.event.EventClient
import com.emarsys.clients.push.DefaultPushClient
import com.emarsys.clients.push.PushClient
import com.emarsys.clients.remoteconfig.DefaultRemoteConfigClient
import com.emarsys.clients.remoteconfig.RemoteConfigClient
import com.emarsys.core.DefaultUrls
import com.emarsys.core.crypto.Crypto
import com.emarsys.core.crypto.DefaultCrypto
import com.emarsys.core.log.SdkLogger
import com.emarsys.core.networking.GenericNetworkClient
import com.emarsys.core.networking.NetworkClient
import com.emarsys.core.providers.DoubleProvider
import com.emarsys.core.providers.TimestampProvider
import com.emarsys.core.providers.UuidProvider
import com.emarsys.core.session.DefaultSessionContext
import com.emarsys.core.session.SessionContext
import com.emarsys.core.state.StateMachine
import com.emarsys.remoteconfig.DefaultRemoteConfigHandler
import com.emarsys.remoteconfig.RemoteConfigHandler
import com.emarsys.setup.SetupOrganizer
import com.emarsys.setup.states.*

abstract class CommonDependencyContainer : DependencyContainer {

    override val stateMachine: StateMachine by lazy {
        val applyRemoteConfigState = ApplyRemoteConfigState(remoteConfigHandler)
        val registerClientState = RegisterClientState(deviceClient)
        val linkContactState = LinkContactState(contactClient, stringStorage, intStorage)
        val registerPushTokenState = RegisterPushTokenState(pushClient, stringStorage)
        val appStartState = AppStartState(eventClient)
        StateMachine(
            listOf(
                applyRemoteConfigState,
                registerClientState,
                registerPushTokenState,
                linkContactState,
                appStartState
            )
        )
    }
    override val setupOrganizer: SetupOrganizer by lazy { SetupOrganizer(stateMachine, sdkContext, sdkLogger) }
    override val defaultUrls: DefaultUrls by lazy {
        DefaultUrls(
            "https://me-client.eservice.emarsys.net",
            "https://mobile-events.eservice.emarsys.net",
            "https://recommender.scarabresearch.com/merchants",
            "https://deep-link.eservice.emarsys.net",
            "https://me-inbox.eservice.emarsys.net",
            "https://mobile-sdk-config.gservice.emarsys.net",
            "https://log-dealer.eservice.emarsys.net"
        )
    }
    override val sessionContext: SessionContext by lazy {
        DefaultSessionContext(
            timestampProvider,
            deviceInfoCollector
        )
    }
    override val contactApi: ContactApi by lazy {
        Contact(
            LoggingContact(sdkLogger),
            LoggingContact(sdkLogger),
            LoggingContact(sdkLogger),
            sdkContext
        )
    }
    override val contactClient: ContactClient by lazy {
        DefaultContactClient(
            emarsysClient,
            sdkContext,
            sessionContext,
            defaultUrls,
            sdkLogger
        )
    }
    override val remoteConfigClient: RemoteConfigClient by lazy {
        DefaultRemoteConfigClient()
    }
    override val eventClient: EventClient by lazy {
        DefaultEventClient(emarsysClient, sdkContext, defaultUrls, sessionContext, timestampProvider)
    }
    override val deviceClient: DeviceClient by lazy {
        DefaultDeviceClient(emarsysClient, sdkContext, defaultUrls, deviceInfoCollector)
    }
    override val pushClient: PushClient by lazy {
        DefaultPushClient(emarsysClient, sdkContext, defaultUrls)
    }
    override val emarsysClient: EmarsysClient by lazy {
        EmarsysClient(
            genericNetworkClient,
            defaultUrls,
            sdkContext,
            sessionContext
        )
    }
    override val genericNetworkClient: NetworkClient by lazy { GenericNetworkClient() }
    override val crypto: Crypto by lazy { DefaultCrypto() }
    override val sdkLogger: SdkLogger by lazy { SdkLogger(defaultUrls) }

    override val uuidProvider: UuidProvider by lazy { UuidProvider() }
    override val doubleProvider: DoubleProvider by lazy { DoubleProvider() }
    override val timestampProvider: TimestampProvider by lazy { TimestampProvider() }
    override val remoteConfigHandler: RemoteConfigHandler by lazy {
        DefaultRemoteConfigHandler(
            deviceInfoCollector,
            remoteConfigClient,
            sdkContext,
            sdkLogger,
            doubleProvider
        )
    }
}