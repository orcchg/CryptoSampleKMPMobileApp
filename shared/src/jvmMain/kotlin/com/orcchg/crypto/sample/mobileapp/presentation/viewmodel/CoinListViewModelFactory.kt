package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin

actual class CoinListViewModelFactory actual constructor(
    private val searchPredicate: (coin: Coin) -> Boolean,
    private val useLocalDataSourceOnly: Boolean
) {
    @Composable
    actual fun create(serviceLocator: ServiceLocator): CoinListViewModel =
        RealCoinListViewModel(
            cryptoRepository =
                if (useLocalDataSourceOnly) {
                    serviceLocator.localCryptoRepository
                } else {
                    serviceLocator.cryptoRepository
                },
            searchPredicate = searchPredicate
        )
}
