package com.emarsys.core.errors

sealed class SdkException(message: String) : RuntimeException(message)

class PreconditionFailed(message: String) : SdkException(message)