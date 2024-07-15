package com.orcchg.crypto.sample.mobileapp.data.source.remote

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import app.cash.paging.RemoteMediator
import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

@OptIn(ExperimentalPagingApi::class)
internal class CoinsRemoteMediator(
    private val local: CoinsDatabaseFacade,
    private val remote: CoinsRemoteFacade
) : RemoteMediator<Int, PricedCoin>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, PricedCoin>
    ): MediatorResult =
        runCatching {
            val offset = when (loadType) {
                LoadType.APPEND -> state.anchorPosition ?: 0
                LoadType.PREPEND -> state.anchorPosition ?: 0
                LoadType.REFRESH -> 0
            }
            val nextOffset = when (loadType) {
                LoadType.APPEND -> offset + Constants.PAGE_LIMIT
                LoadType.PREPEND -> offset - Constants.PAGE_LIMIT
                LoadType.REFRESH -> 0
            }

            remote.coins(offset = offset, limit = Constants.PAGE_LIMIT)
                .also { local.append(it.coins) } // update backing dataset
                .let {
                    val endOfPaginationReached = nextOffset >= it.total
                    MediatorResult.Success(endOfPaginationReached)
                }
        }
            .getOrElse { e -> MediatorResult.Error(e) }

    override suspend fun initialize(): InitializeAction =
        if (local.isEmptyOrExpired()) {
            InitializeAction.LAUNCH_INITIAL_REFRESH
        } else {
            InitializeAction.SKIP_INITIAL_REFRESH
        }
}
