package com.orcchg.crypto.sample.mobileapp.data.source.local

import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal interface CoinsDatabaseFacade {
    suspend fun isEmptyOrExpired(): Boolean
    suspend fun coins(limit: Int, offset: Int): CoinsPage
    suspend fun favouriteCoins(limit: Int, offset: Int): CoinsPage
    suspend fun search(searchTerm: String, limit: Int, offset: Int): CoinsPage
    suspend fun insert(coins: List<PricedCoin>)
    suspend fun deleteAll()
}
