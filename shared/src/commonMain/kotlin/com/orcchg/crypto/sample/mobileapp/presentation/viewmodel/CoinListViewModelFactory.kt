package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin

expect class CoinListViewModelFactory(
    results: CoinListResults,
    searchPredicate: (coin: Coin) -> Boolean = { true }
) {
    @Composable
    fun create(serviceLocator: ServiceLocator): CoinListViewModel
}
