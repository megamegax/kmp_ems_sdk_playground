package com.emarsys.core.providers

import com.benasher44.uuid.uuid4

class UuidProvider : Provider<String> {
    override fun provide(): String {
        return uuid4().toString()
    }
}