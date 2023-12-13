package com.emarsys.clients.event.model

import com.emarsys.core.models.GenericAction
import kotlinx.serialization.Serializable

@Serializable
data class OnEventActionResponse
    (
    val campaignId: String,
    val actions: List<GenericAction>
)

