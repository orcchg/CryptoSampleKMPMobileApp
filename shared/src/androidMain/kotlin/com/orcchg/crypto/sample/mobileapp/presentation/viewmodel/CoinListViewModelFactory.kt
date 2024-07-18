package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin

actual class CoinListViewModelFactory actual constructor(
    private val resultsType: CoinListResults,
    private val searchPredicate: (coin: Coin) -> Boolean

) {
    @Composable
    actual fun create(serviceLocator: ServiceLocator): CoinListViewModel =
        viewModel(
            factory = createViewModelFactory {
                RealCoinListViewModel(
                    cryptoRepository = serviceLocator.cryptoRepository(resultsType),
                    searchPredicate = searchPredicate
                )
            }
        )
}
