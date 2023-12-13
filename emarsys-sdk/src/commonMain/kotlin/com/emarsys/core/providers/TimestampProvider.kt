package com.emarsys.core.providers

import kotlinx.datetime.Clock

class TimestampProvider : Provider<Long> {
    override fun provide(): Long {
        return Clock.System.now().epochSeconds
    }
}