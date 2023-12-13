package com.emarsys.sample

import android.app.Application
import com.emarsys.Emarsys
import com.emarsys.EmarsysConfig
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.newSingleThreadContext

class MyApplication : Application() {
    private val sdkScope = CoroutineScope(newSingleThreadContext("SDK"))
    override fun onCreate() {
        super.onCreate()
        sdkScope.launch {
            Emarsys.initialize()

            Emarsys.enableTracking(EmarsysConfig("EMS11-C3FD3"))
        }
    }
}