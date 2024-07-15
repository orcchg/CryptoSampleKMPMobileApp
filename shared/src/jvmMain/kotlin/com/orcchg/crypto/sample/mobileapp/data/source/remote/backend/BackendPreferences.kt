package com.orcchg.crypto.sample.mobileapp.data.source.remote.backend

actual class BackendPreferences {
    actual val baseUrl: String = System.getProperty("custom.backendUrl")
}