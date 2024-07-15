package com.orcchg.crypto.sample.mobileapp.data.source.base

import androidx.paging.PagingState
import app.cash.paging.PagingSource
import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal abstract class BaseCoinsPagingSource(
    private val coinsProvider: suspend (offset: Int, limit: Int) -> CoinsPage
) : PagingSource<Int, PricedCoin>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PricedCoin> =
        runCatching {
            val offset = params.key ?: 0
            coinsProvider(offset, Constants.PAGE_LIMIT)
                .let { p ->
                    LoadResult.Page(
                        data = p.coins,
                        prevKey = (offset - Constants.PAGE_LIMIT).takeUnless { it < 0 },
                        nextKey = (offset + Constants.PAGE_LIMIT).takeUnless { it >= p.total },
                        itemsBefore = offset.coerceAtLeast(0),
                        itemsAfter = (p.total - offset).coerceAtLeast(0)
                    )
                }
        }
            .getOrElse { e ->
                LoadResult.Error(e)
            }

    override fun getRefreshKey(state: PagingState<Int, PricedCoin>): Int? =
        (state.anchorPosition ?: 0)
            .takeIf { state.pages.size > it }
            ?.let { anchorPosition ->
                ((state.pages[anchorPosition].nextKey ?: 0) - Constants.PAGE_LIMIT.rotateRight(1))
                    .coerceAtLeast(0)
            } // 'null' means 'load()' will decide the default key
}
