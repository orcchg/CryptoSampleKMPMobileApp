package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModel
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.RealCoinListViewModel

@Composable
fun CoinListScreen(
    serviceLocator: ServiceLocator,
    viewModel: CoinListViewModel = RealCoinListViewModel(
        cryptoRepository = serviceLocator.cryptoRepository
    )
        .apply { start() }
) {}
