package com.orcchg.crypto.sample.mobileapp.data.repository

import app.cash.paging.Pager
import app.cash.paging.RemoteMediator
import com.orcchg.crypto.sample.mobileapp.data.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.http.DefaultHttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.http.HttpClientProvider
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository

internal class RealCryptoRepository(
    private val backendPreferences: BackendPreferences,
    httpClientProvider: HttpClientProvider = DefaultHttpClientProvider()
) : CryptoRepository {
    val x: RemoteMediator

    private val pager by lazy(LazyThreadSafetyMode.NONE) {
        Pager()
    }
}
