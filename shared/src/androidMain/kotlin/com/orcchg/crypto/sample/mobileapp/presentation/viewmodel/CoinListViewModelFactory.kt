package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator

actual class CoinListViewModelFactory {
    @Composable
    actual fun create(serviceLocator: ServiceLocator): CoinListViewModel =
        viewModel(
            factory = createViewModelFactory {
                RealCoinListViewModel(
                    cryptoRepository = serviceLocator.cryptoRepository
                )
            }
        )
}
