package com.orcchg.crypto.sample.mobileapp.data.source.local

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import app.cash.sqldelight.paging3.QueryPagingSource
import com.orcchg.crypto.sample.mobileapp.common.ioDispatcher
import com.orcchg.crypto.sample.mobileapp.data.source.base.CoinPagingSource
import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

/**
 * Local only [PagingSource] for a paginated list of [PricedCoin].
 */
internal class PersistentCoinsPagingLocalSource(
    private val database: CryptoSampleKMPDatabase
) : CoinPagingSource() {

    private val pagingSource by lazy(LazyThreadSafetyMode.NONE) {
        with (database) {
            QueryPagingSource(
                countQuery = coinDaoQueries.count(),
                transacter = coinDaoQueries,
                context = ioDispatcher(),
                queryProvider = coinDaoQueries::select
            )
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PricedCoin> =
        pagingSource.load(params).let { loadResult ->
            when (loadResult) {
                is LoadResult.Page ->
                    with (loadResult) {
                        LoadResult.Page(
                            data = data.map(CoinDaoToDomainMapper::toDomain),
                            prevKey = prevKey,
                            nextKey = nextKey,
                            itemsBefore = itemsBefore,
                            itemsAfter = itemsAfter
                        )
                    }
                is LoadResult.Error -> LoadResult.Error(loadResult.throwable)
                is LoadResult.Invalid -> LoadResult.Invalid()
            }
        }

    override fun getRefreshKey(state: PagingState<Int, PricedCoin>): Int? =
        pagingSource.getRefreshKey(
            PagingState(
                pages = state.pages.map { page ->
                    with(page) {
                        LoadResult.Page(
                            data = data.map(CoinDaoToDomainMapper::fromDomain),
                            prevKey = prevKey,
                            nextKey = nextKey,
                            itemsBefore = itemsBefore,
                            itemsAfter = itemsAfter
                        )
                    }
                },
                anchorPosition = state.anchorPosition,
                config = state.config,
                leadingPlaceholderCount = 0
            )
        )
}