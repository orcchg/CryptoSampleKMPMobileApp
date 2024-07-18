package com.orcchg.crypto.sample.mobileapp.data.source.local

import app.cash.paging.PagingSource
import com.orcchg.crypto.sample.mobileapp.data.source.base.BaseCoinsPagingSource
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

/**
 * Local only [PagingSource] for a paginated list of [PricedCoin].
 */
internal class CoinsPagingLocalSource(
    local: CoinsDatabaseFacade
) : BaseCoinsPagingSource(local::coins)

internal class FavouritesCoinsPagingLocalSource(
    local: CoinsDatabaseFacade
) : BaseCoinsPagingSource(local::favouriteCoins)

internal class SearchCoinsPagingLocalSource(
    local: CoinsDatabaseFacade,
    searchTerm: String
) : BaseCoinsPagingSource({ limit, offset ->
    local.search(searchTerm = searchTerm, limit = limit, offset = offset)
})
