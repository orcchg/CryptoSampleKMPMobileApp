package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
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
        retrieve(items = data, offset = offset, limit = limit)

    override suspend fun favouriteCoins(offset: Int, limit: Int): CoinsPage =
        retrieve(
            items = data.filter { it.isFavourite },
            offset = offset,
            limit = limit
        )

    override suspend fun search(searchTerm: String, offset: Int, limit: Int): CoinsPage =
        retrieve(
            items = data.filter { it.symbol.startsWith(searchTerm) || it.name.startsWith(searchTerm) },
            offset = offset,
            limit = limit
        )

    override suspend fun append(coins: List<PricedCoin>) {
        if (coins.isEmpty()) {
            return
        }

        data.addAll(coins.map(CoinDaoToDomainMapper::fromDomain))
        if (createdAt <= 0L) {
            createdAt = Clock.System.now().toEpochMilliseconds()
        }
    }

    override suspend fun deleteAll() {
        data.clear()
        createdAt = 0L
    }

    private fun checkLimitAndOffset(offset: Int, limit: Int) {
        if (limit < 0 || offset < 0) {
            throw IllegalArgumentException("limit $limit and offset $offset must not be negative")
        }
    }

    private fun retrieve(items: List<CoinDao>, offset: Int, limit: Int): CoinsPage {
        checkLimitAndOffset(offset = offset, limit = limit)
        if (items.isEmpty() || limit == 0 || offset >= items.size) {
            return CoinsPage(
                coins = emptyList(),
                offset = offset,
                total = items.size
            )
        }
        val coins = items.subList(offset, (offset + limit).coerceAtMost(items.size))
        return CoinsPage(
            coins = coins.map(CoinDaoToDomainMapper::toDomain),
            offset = offset,
            total = items.size
        )
    }
}
