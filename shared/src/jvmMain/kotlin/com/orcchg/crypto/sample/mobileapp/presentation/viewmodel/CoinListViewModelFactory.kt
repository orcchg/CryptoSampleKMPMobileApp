package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin

actual class CoinListViewModelFactory actual constructor(
    private val results: CoinListResults,
    private val searchPredicate: (coin: Coin) -> Boolean
) {
    @Composable
    actual fun create(serviceLocator: ServiceLocator): CoinListViewModel =
        RealCoinListViewModel(
            cryptoRepository = serviceLocator.cryptoRepository(results),
            searchPredicate = searchPredicate
        )
}
