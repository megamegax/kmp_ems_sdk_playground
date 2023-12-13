package com.emarsys.core.providers

import com.benasher44.uuid.uuid4
import kotlin.random.Random

class DoubleProvider : Provider<Double> {
    override fun provide(): Double {
        return Random.nextDouble()
    }
}