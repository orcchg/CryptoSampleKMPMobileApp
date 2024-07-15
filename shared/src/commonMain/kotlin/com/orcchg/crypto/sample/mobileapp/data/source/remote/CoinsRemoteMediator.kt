package com.orcchg.crypto.sample.mobileapp.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import app.cash.paging.RemoteMediator
import com.orcchg.crypto.sample.mobileapp.data.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.http.DefaultHttpClientProvider
import com.orcchg.crypto.sample.mobileapp.data.http.HttpClientProvider
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

@OptIn(ExperimentalPagingApi::class)
internal class CoinsRemoteMediator(
    private val backendPreferences: BackendPreferences,
    httpClientProvider: HttpClientProvider = DefaultHttpClientProvider()
) : RemoteMediator<Int, PricedCoin>() {

    private val client by lazy(LazyThreadSafetyMode.NONE) {
        httpClientProvider.httpClient()
    }

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PricedCoin>
    ): MediatorResult =
        runCatching {
            when (loadType) {
                LoadType.REFRESH -> null
                LoadType.APPEND ->
            }
        }
}
