package com.emarsys

import com.emarsys.core.errors.PreconditionFailed
import com.emarsys.core.stash.ConfigEntity
import com.emarsys.core.storage.Stashable

data class EmarsysConfig(val applicationCode: String?, val merchantId: String?) :
    Stashable<ConfigEntity> {
    fun EmarsysConfig.toEntity(): ConfigEntity {
        return ConfigEntity(applicationCode, merchantId)
    }

    companion object {
        fun EmarsysConfig.fromEntity(entity: ConfigEntity): EmarsysConfig {
            return EmarsysConfig(entity.applicationCode, entity.merchantId)
        }
    }
}

fun EmarsysConfig.isValid(): Boolean {
    val invalidCases = listOf("null", "", "0", "test")
    if (invalidCases.contains(applicationCode)) {
        throw PreconditionFailed("ApplicationCode should be valid!")
    }
    if (invalidCases.contains(merchantId)) {
        throw PreconditionFailed("MerchantId should be valid!")
    }
    if (applicationCode == null && merchantId == null) {
        throw PreconditionFailed("ApplicationCode or MerchantId must be present for Tracking!")
    }
    return true
}