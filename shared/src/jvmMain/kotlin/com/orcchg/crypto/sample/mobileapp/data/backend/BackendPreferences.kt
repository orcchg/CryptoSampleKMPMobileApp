package com.orcchg.crypto.sample.mobileapp.data.backend

actual class BackendPreferences {
    actual val baseUrl: String = System.getProperty("custom.backendUrl")
}
