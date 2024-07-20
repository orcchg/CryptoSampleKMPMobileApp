package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import co.touchlab.kermit.Logger
import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import kotlinx.datetime.Clock
import kotlin.math.abs

internal class InMemoryCoinsDatabaseFacade : CoinsDatabaseFacade {
    private val data = mutableListOf<CoinDao>()

    override suspend fun isEmptyOrExpired(): Boolean =
        data.isEmpty() || abs(Clock.System.now().toEpochMilliseconds() - (data.minByOrNull { it.createdAt }?.createdAt ?: 0L)) >= Constants.CACHE_EXPIRATION_MILLIS

    override suspend fun coins(limit: Int, offset: Int): CoinsPage =
        sublist(items = data, limit = limit, offset = offset)

    override suspend fun favouriteCoins(limit: Int, offset: Int): CoinsPage =
        sublist(
            items = data.filter { it.isFavourite },
            offset = offset,
            limit = limit
        )

    override suspend fun search(searchTerm: String, limit: Int, offset: Int): CoinsPage =
        sublist(
            items = data.filter { it.symbol.startsWith(searchTerm) || it.name.startsWith(searchTerm) },
            offset = offset,
            limit = limit
        )

    override suspend fun insert(coins: List<PricedCoin>) {
        Logger.d { "cache [${this::class.simpleName}] :: insert(${coins.size})" }
        if (coins.isEmpty()) {
            return
        }

        data.addAll(coins.map(CoinDaoToDomainMapper::fromDomain))
    }

    override suspend fun updateFavourite(coinIndex: Long, isFavourite: Boolean) {
        Logger.d { "cache [${this::class.simpleName}] :: updateFavourite($coinIndex, $isFavourite)" }
        data.indexOfFirst { it.id == coinIndex }
            .takeIf { it != -1 }
            ?.let {
                val newItem = data[it].copy(isFavourite = isFavourite)
                data.set(it, newItem)
            }
    }

    override suspend fun deleteAll() {
        Logger.d { "cache [${this::class.simpleName}] :: deleteAll" }
        data.clear()
    }

    private fun sublist(items: List<CoinDao>, limit: Int, offset: Int): CoinsPage {
        checkLimitAndOffset(limit = limit, offset = offset)
        return checkSize(size = items.size, limit = limit, offset = offset) ?: run {
            val coins = items.subList(offset, (offset + limit).coerceAtMost(items.size))
            CoinsPage(
                coins = coins.map(CoinDaoToDomainMapper::toDomain),
                offset = offset,
                total = items.size
            )
        }
    }
}
