package com.emarsys

import io.kotest.matchers.shouldBe
import kotlin.test.Test

class EmarsysConfigTest {

    @Test
    fun testEmarsysConfig_returns_correct_value() {
        val config = EmarsysConfig("appId", "merchantId")

        config.applicationCode shouldBe "appId"
        config.merchantId shouldBe "merchantId"
        config.isValid() shouldBe true
    }
}