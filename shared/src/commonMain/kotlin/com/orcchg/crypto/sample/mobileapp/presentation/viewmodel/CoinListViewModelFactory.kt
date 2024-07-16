package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator

expect class CoinListViewModelFactory() {
    @Composable
    fun create(serviceLocator: ServiceLocator): CoinListViewModel
}
