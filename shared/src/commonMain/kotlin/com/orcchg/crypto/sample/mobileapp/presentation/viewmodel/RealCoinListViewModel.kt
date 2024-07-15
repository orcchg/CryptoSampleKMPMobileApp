package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import app.cash.paging.PagingData
import com.orcchg.crypto.sample.mobileapp.common.ioDispatcher
import com.orcchg.crypto.sample.mobileapp.common.viewmodel.ViewModel
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

internal class RealCoinListViewModel(
    private val cryptoRepository: CryptoRepository,
    private val dispatcher: CoroutineDispatcher = ioDispatcher()
) : ViewModel(), CoinListViewModel {

    private var offset: Int = 0
    private var total: Int = 0

    private val _items = MutableStateFlow<PagingData<CoinVo>>()
    override val items: StateFlow<List<CoinVo>> = _items

    override fun start() {
        viewModelScope.launch(dispatcher) {
            cryptoRepository.coins(limit = PAGE_LIMIT, offset = offset)
                .let { page ->
                    offset += page.coins.size
                    total = page.total
                    //
                }
        }
    }

    override fun hasMoreItems(): Boolean =
        total > offset + PAGE_LIMIT

    companion object {
        private const val PAGE_LIMIT = 30
    }
}
