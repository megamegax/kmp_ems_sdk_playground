package com.emarsys.core.state

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

interface StateContext {
    var stateLifecycle: MutableStateFlow<Pair<String, StateLifecycle>?>
}