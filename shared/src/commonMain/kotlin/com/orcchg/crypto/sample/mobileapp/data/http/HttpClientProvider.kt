package com.orcchg.crypto.sample.mobileapp.data.http

import io.ktor.client.HttpClient

internal interface HttpClientProvider {
    fun httpClient(): HttpClient
}
