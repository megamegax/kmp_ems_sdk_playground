package com.emarsys.api

import com.emarsys.context.SdkContext
import com.emarsys.core.state.SdkState
import com.emarsys.features.Feature
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async

open class GenericApi(
    protected val loggingInstance: ActivationAware,
    protected val gathererInstance: ActivationAware,
    protected val internalInstance: ActivationAware,
    override val sdkContext: SdkContext
) : Api {
    var active: ActivationAware? = null
        set(value) {
            if (value != null) {
                GlobalScope.async(Dispatchers.Default) {
                    value.activated()
                }
            }
            field = value
        }


    init {
        active = loggingInstance
    }

    fun internalInstance(features: List<Feature>): ActivationAware {
        var result: ActivationAware = loggingInstance
        if (features.contains(Feature.MOBILE_ENGAGE)) {
            result = internalInstance
        }
        return result
    }

    fun setActiveInstance(state: SdkState, features: List<Feature>) {
        when (state) {
            SdkState.ACTIVE -> this.active = internalInstance(features)
            SdkState.INACTIVE -> this.active = loggingInstance
            SdkState.ON_HOLD -> this.active = gathererInstance
        }
    }
}