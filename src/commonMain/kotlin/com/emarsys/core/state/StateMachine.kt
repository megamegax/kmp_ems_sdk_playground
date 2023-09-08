package com.emarsys.core.state

import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class StateMachine(private val states: List<State>) : StateContext {
    override var stateLifecycle: MutableStateFlow<Pair<String, StateLifecycle>?> = MutableStateFlow(null)
    suspend fun activate() {
        states.forEach {
            stateLifecycle.value = Pair(it.name, StateLifecycle.PREPARE)
            it.prepare()
            stateLifecycle.value = Pair(it.name, StateLifecycle.ACTIVATE)
            it.active()
            it.relax()
            stateLifecycle.value = Pair(it.name, StateLifecycle.RELAXED)
        }
    }
}