@file:Suppress("ktlint:standard:no-wildcard-imports")

package com.orcchg.crypto.sample.mobileapp.data.source.remote.http

import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

internal class DefaultHttpClientProvider(
    timeout: Long = 30_000L,
    config: HttpClientConfig<*>.() -> Unit = {},
) : HttpClientProvider {

    private val client by lazy(LazyThreadSafetyMode.NONE) {
        HttpClient {
            expectSuccess = true
            install(ContentNegotiation) {
                json(
                    Json {
                        ignoreUnknownKeys = true
                    },
                )
            }
            install(HttpTimeout) {
                requestTimeoutMillis = timeout
                connectTimeoutMillis = timeout
                socketTimeoutMillis = timeout
            }
            config()
        }
    }

    override fun httpClient(): HttpClient = client
}
