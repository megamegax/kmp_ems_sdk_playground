package com.emarsys.di

import android.app.Application
import android.app.NotificationManager
import android.content.Context
import android.content.SharedPreferences
import androidx.core.app.NotificationManagerCompat
import com.emarsys.context.SdkContext
import com.emarsys.core.SdkConfig
import com.emarsys.core.device.DeviceInfoCollector
import com.emarsys.core.notification.NotificationManagerProxy
import com.emarsys.core.storage.IntSecureStorage
import com.emarsys.core.storage.SecureSharedPreferences
import com.emarsys.core.storage.SecureStorage
import com.emarsys.core.storage.StringSecureStorage

class AndroidDependencyContainer : DependencyContainer, CommonDependencyContainer() {
    override val sdkConfig: SdkConfig by lazy { SdkConfig.load() }
    override val sdkContext: SdkContext by lazy { SdkContext(sdkConfig, defaultUrls) }
    val application: Application by lazy {
        Class.forName("android.app.ActivityThread")
            .getMethod("currentApplication").invoke(null) as Application
    }
    val notificationManager =
        application.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

    val notificationManagerProxy: NotificationManagerProxy by lazy {
        NotificationManagerProxy(
            notificationManager,
            NotificationManagerCompat.from(application)
        )
    }
    override val deviceInfoCollector: DeviceInfoCollector by lazy { DeviceInfoCollector(notificationManagerProxy) }
    val sharedPreferences: SharedPreferences by lazy {
        SecureSharedPreferences.create(
            "emarsys-sdk",
            application
        )
    }
    override val stringStorage: SecureStorage<String> by lazy {
        StringSecureStorage(sharedPreferences)
    }
    override val intStorage: SecureStorage<Int> by lazy {
        IntSecureStorage(sharedPreferences)
    }


}