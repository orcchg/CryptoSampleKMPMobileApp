package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator

actual class CoinListViewModelFactory  {
    @Composable
    actual fun create(serviceLocator: ServiceLocator): CoinListViewModel =
        RealCoinListViewModel(
            cryptoRepository = serviceLocator.cryptoRepository
        )
}
