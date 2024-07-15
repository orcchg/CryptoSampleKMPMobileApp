package com.orcchg.crypto.sample.mobileapp.data.source.remote

import app.cash.paging.PagingSource
import com.orcchg.crypto.sample.mobileapp.data.source.base.BaseCoinsPagingSource
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

/**
 * Remote only [PagingSource] for a paginated list of [PricedCoin].
 */
internal class CoinsPagingRemoteSource(
    private val remote: CoinsRemoteFacade
) : BaseCoinsPagingSource(coinsProvider = remote::coins)
