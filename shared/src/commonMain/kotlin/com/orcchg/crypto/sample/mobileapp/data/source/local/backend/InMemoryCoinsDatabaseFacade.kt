package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import kotlinx.datetime.Clock
import kotlin.math.abs

internal class InMemoryCoinsDatabaseFacade : CoinsDatabaseFacade {
    private var createdAt: Long = 0L
    private val data = mutableListOf<CoinDao>()

    override suspend fun isEmptyOrExpired(): Boolean =
        data.isEmpty() || abs(Clock.System.now().toEpochMilliseconds() - createdAt) >= Constants.CACHE_EXPIRATION_MILLIS

    override suspend fun coins(offset: Int, limit: Int): CoinsPage =
        CoinsPage(
            coins = data.subList(offset, offset + limit),
            offset = offset,
            total = data.size
        )

    override suspend fun favouriteCoins(offset: Int, limit: Int): CoinsPage {
        TODO("Not yet implemented")
    }

    override suspend fun search(searchTerm: String, offset: Int, limit: Int): CoinsPage {
        TODO("Not yet implemented")
    }

    override suspend fun append(coins: List<PricedCoin>) {
        if (coins.isEmpty()) {
            return
        }

        data.addAll(coins)
        if (createdAt <= 0L) {
            createdAt = Clock.System.now().toEpochMilliseconds()
        }
    }

    override suspend fun deleteAll() {
        data.clear()
        createdAt = 0L
    }
}
