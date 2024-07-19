package com.orcchg.crypto.sample.mobileapp.data.repository

import androidx.paging.ExperimentalPagingApi
import app.cash.paging.Pager
import app.cash.paging.PagingConfig
import app.cash.paging.PagingData
import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.base.CoinPagingSource
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteMediator
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.Flow

internal class RealCryptoRepository(
    private val local: CoinsDatabaseFacade,
    private val remote: CoinsRemoteFacade,
    private val useAsLocalOnlyDataSource: Boolean,
    private val pagingSourceFactory: () -> CoinPagingSource
) : CryptoRepository {

    @OptIn(ExperimentalPagingApi::class)
    private val pager by lazy(LazyThreadSafetyMode.NONE) {
        Pager(
            config = PagingConfig(
                pageSize = Constants.PAGE_LIMIT,
                prefetchDistance = Constants.PREFETCH_LIMIT,
                initialLoadSize = Constants.PAGE_LIMIT
            ),
            remoteMediator = CoinsRemoteMediator(local, remote).takeUnless { useAsLocalOnlyDataSource },
            pagingSourceFactory = pagingSourceFactory
        )
    }

    // TODO: set favourites statuses
    private val _coinsPages by lazy(LazyThreadSafetyMode.NONE) { pager.flow }
    override val coinsPages: Flow<PagingData<PricedCoin>> = _coinsPages

    override suspend fun setFavourite(coinIndex: Long, isFavourite: Boolean) =
        local.updateFavourite(coinIndex, isFavourite)
}
