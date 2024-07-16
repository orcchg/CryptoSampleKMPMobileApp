package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin

expect class CoinListViewModelFactory(
    searchPredicate: (coin: Coin) -> Boolean = { true },
    useLocalDataSourceOnly: Boolean = false
) {
    @Composable
    fun create(serviceLocator: ServiceLocator): CoinListViewModel
}
