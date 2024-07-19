package com.orcchg.crypto.sample.mobileapp.data.source.remote.backend

actual class BackendPreferences actual constructor() {
    actual val baseUrl: String = System.getProperty("custom.backendUrl") ?: "http://localhost:8080"
}
