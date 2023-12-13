package com.emarsys.core.log

data class LogEntry(
    val topic: String,
    val data: Map<String, Any?>,
    val hideSensitiveData: Boolean = false
) {
    companion object {

        fun <T> createMethodNotAllowedEntry(
            source: T,
            functionName: String? = null,
            params: Map<String, Any?>? = null
        ): LogEntry {
            val data: MutableMap<String, Any?> = mutableMapOf(
                "className" to this::class.simpleName,
                "methodName" to "functionName"
            )


            if (params != null) {
                data["parameters"] = params
            }

            return LogEntry("log_method_not_allowed", data)
        }
    }
}