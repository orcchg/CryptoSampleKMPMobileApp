package com.orcchg.crypto.sample.mobileapp.presentation.viewmodel

import app.cash.paging.PagingData
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import kotlinx.coroutines.flow.Flow

interface CoinListViewModel {
    val items: Flow<PagingData<CoinVo>>
}
