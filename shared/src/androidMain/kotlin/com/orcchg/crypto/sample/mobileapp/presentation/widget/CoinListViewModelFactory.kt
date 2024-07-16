package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModel
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.RealCoinListViewModel
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.createViewModelFactory

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