package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import app.cash.paging.PagingData
import app.cash.paging.cachedIn
import app.cash.paging.map
import com.orcchg.crypto.sample.mobileapp.common.viewmodel.ViewModel
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import com.orcchg.crypto.sample.mobileapp.presentation.model.mapper.PricedCoinToCoinVoMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

internal class RealCoinListViewModel(
    private val cryptoRepository: CryptoRepository
) : ViewModel(), CoinListViewModel {

    override val items: Flow<PagingData<CoinVo>> by lazy(LazyThreadSafetyMode.NONE) {
        cryptoRepository.coinsPages.map { page ->
            page.map(PricedCoinToCoinVoMapper::toVo)
        }
            .cachedIn(viewModelScope)
    }

    override fun setFavourite(coinIndex: Long, isFavourite: Boolean) {
        viewModelScope.launch {
            cryptoRepository.setFavourite(coinIndex, isFavourite)
        }
    }
}
