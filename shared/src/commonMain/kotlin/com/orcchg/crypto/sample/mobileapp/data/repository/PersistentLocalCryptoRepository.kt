package com.orcchg.crypto.sample.mobileapp.data.repository

import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.PersistentCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow

internal class PersistentLocalCryptoRepository(
    private val database: CryptoSampleKMPDatabase
) : CryptoRepository {

    private val pager by lazy(LazyThreadSafetyMode.NONE) {
        Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_LIMIT,
                prefetchDistance = Constants.PREFETCH_LIMIT,
                initialLoadSize = Constants.PAGE_LIMIT
            ),
        ) {
            PersistentCoinsPagingLocalSource(database)
        }
    }

    private val _coinsPages by lazy(LazyThreadSafetyMode.NONE) { pager.flow }
    override val coinsPages: Flow<PagingData<PricedCoin>> = _coinsPages
}
