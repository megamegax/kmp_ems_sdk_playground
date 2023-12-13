package com.emarsys.core.notification

import android.os.Build
import androidx.annotation.RequiresApi
import com.emarsys.core.device.ChannelSettings
import com.emarsys.core.device.NotificationSettings

import java.util.*


class NotificationManagerHelper(private val notificationManagerProxy: NotificationManagerProxy) :
    NotificationSettings {

    override val areNotificationsEnabled: Boolean
        get() = notificationManagerProxy.areNotificationsEnabled

    override val importance: Int
        get() = notificationManagerProxy.importance

    @get:RequiresApi(api = Build.VERSION_CODES.O)
    override val channelSettings: List<ChannelSettings>
        get() = notificationManagerProxy.notificationChannels

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || javaClass != other.javaClass) return false
        val that = other as NotificationManagerHelper
        return notificationManagerProxy == that.notificationManagerProxy
    }

    override fun hashCode(): Int {
        return Objects.hash(notificationManagerProxy)
    }
}