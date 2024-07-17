package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import com.orcchg.crypto.sample.mobileapp.common.viewmodel.ViewModel
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import com.orcchg.crypto.sample.mobileapp.presentation.model.mapper.PricedCoinToCoinVoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class RealCoinListViewModel(
    private val cryptoRepository: CryptoRepository,
    private val searchPredicate: (coin: Coin) -> Boolean
) : ViewModel(), CoinListViewModel {

    override val items: Flow<PagingData<CoinVo>> by lazy(LazyThreadSafetyMode.NONE) {
        cryptoRepository.coinsPages.map { page ->
            page.map(PricedCoinToCoinVoMapper::toVo)
        }
            .cachedIn(viewModelScope)
    }
}
