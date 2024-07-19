package com.orcchg.crypto.sample.mobileapp.data.source.remote.backend

import com.orcchg.crypto.sample.mobileapp.shared.BuildConfig

actual class BackendPreferences actual constructor() {
    actual val baseUrl: String = BuildConfig.BACKEND_URL
}
