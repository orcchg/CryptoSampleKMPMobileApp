package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults

expect class CoinListViewModelFactory(
    resultsType: CoinListResults
) {
    @Composable
    fun create(serviceLocator: ServiceLocator): CoinListViewModel
}
