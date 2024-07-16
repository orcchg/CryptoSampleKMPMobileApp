package com.orcchg.crypto.sample.mobileapp.presentation.widget

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.viewmodel.CoinListViewModel

expect class CoinListViewModelFactory() {
    @Composable
    fun create(serviceLocator: ServiceLocator): CoinListViewModel
}