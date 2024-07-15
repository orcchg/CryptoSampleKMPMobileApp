package com.orcchg.crypto.sample.mobileapp.data.source.local

import app.cash.paging.PagingSource
import com.orcchg.crypto.sample.mobileapp.data.source.base.BaseCoinsPagingSource
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

/**
 * Local only [PagingSource] for a paginated list of [PricedCoin].
 */
internal class CoinsPagingLocalSource(
    private val local: CoinsDatabaseFacade
) : BaseCoinsPagingSource(local::coins)
