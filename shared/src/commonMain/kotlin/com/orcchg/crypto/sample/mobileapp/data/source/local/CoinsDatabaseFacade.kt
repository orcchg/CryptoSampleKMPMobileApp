package com.orcchg.crypto.sample.mobileapp.data.source.local

import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal interface CoinsDatabaseFacade {
    suspend fun isEmptyOrExpired(): Boolean
    suspend fun coins(offset: Int, limit: Int): CoinsPage
    suspend fun favouriteCoins(offset: Int, limit: Int): CoinsPage
    suspend fun search(searchTerm: String, offset: Int, limit: Int): CoinsPage
    suspend fun append(coins: List<PricedCoin>)
    suspend fun deleteAll()
}
