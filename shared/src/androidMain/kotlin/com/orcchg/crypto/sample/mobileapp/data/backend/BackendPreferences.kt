package com.orcchg.crypto.sample.mobileapp.data.backend

import com.orcchg.crypto.sample.mobileapp.shared.BuildConfig

actual class BackendPreferences {
    actual val baseUrl: String = BuildConfig.BACKEND_URL
}
