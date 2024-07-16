package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModel
import com.orcchg.crypto.sample.mobileapp.presentation.widget.CoinListViewModelFactory

@Composable
fun CoinListScreen(
    serviceLocator: ServiceLocator,
    viewModel: CoinListViewModel = CoinListViewModelFactory().create(serviceLocator)
) {
}
