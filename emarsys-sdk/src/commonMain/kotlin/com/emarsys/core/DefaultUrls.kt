package com.emarsys.core

data class DefaultUrls(
    override val clientServiceBaseUrl: String,
    override val eventServiceBaseUrl: String,
    override val predictBaseUrl: String,
    override val deepLinkBaseUrl: String,
    override val inboxBaseUrl: String,
    override val remoteConfigBaseUrl: String,
    override val loggingUrl: String
) : IDefaultUrls

interface IDefaultUrls {
    val clientServiceBaseUrl: String
    val eventServiceBaseUrl: String
    val predictBaseUrl: String
    val deepLinkBaseUrl: String
    val inboxBaseUrl: String
    val remoteConfigBaseUrl: String
    val loggingUrl: String
}