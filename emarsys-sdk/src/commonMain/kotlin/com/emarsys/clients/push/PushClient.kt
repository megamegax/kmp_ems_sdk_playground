package com.emarsys.clients.push

interface PushClient {
    suspend fun registerPushToken(pushToken: String)
    suspend fun removePushToken()
}