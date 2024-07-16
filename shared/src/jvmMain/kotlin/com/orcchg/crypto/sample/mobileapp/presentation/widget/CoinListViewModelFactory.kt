package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModel
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.RealCoinListViewModel

actual class CoinListViewModelFactory  {
    @Composable
    actual fun create(serviceLocator: ServiceLocator): CoinListViewModel =
        RealCoinListViewModel(
            cryptoRepository = serviceLocator.cryptoRepository
        )
}