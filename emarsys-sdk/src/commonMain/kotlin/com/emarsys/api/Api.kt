package com.emarsys.api

import com.emarsys.context.SdkContext
import kotlinx.coroutines.CancellableContinuation

interface Api {

    val sdkContext: SdkContext

}