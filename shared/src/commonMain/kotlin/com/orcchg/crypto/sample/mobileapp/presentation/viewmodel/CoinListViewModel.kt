package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import kotlinx.coroutines.flow.StateFlow

interface CoinListViewModel {
    val items: StateFlow<List<CoinVo>>

    fun start()
    fun hasMoreItems(): Boolean
}
