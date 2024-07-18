package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import kotlinx.datetime.Clock
import kotlin.math.abs

internal class RealCoinsDatabaseFacade(
    private val database: CryptoSampleKMPDatabase
) : CoinsDatabaseFacade {

    override suspend fun isEmptyOrExpired(): Boolean =
        (database.coinDaoQueries.count().executeAsOneOrNull() ?: 0L) <= 0L ||
        abs(Clock.System.now().toEpochMilliseconds() - (database.coinDaoQueries.minByCreatedAt().executeAsOneOrNull()?.createdAt ?: 0L)) >= Constants.CACHE_EXPIRATION_MILLIS

    override suspend fun coins(limit: Int, offset: Int): CoinsPage {
        checkLimitAndOffset(limit = limit, offset = offset)
        val total = database.coinDaoQueries.count()
            .executeAsOneOrNull()
            ?.toInt()
            ?: 0

        return checkSize(size = total, limit = limit, offset = offset) ?:
            database.coinDaoQueries.select(
                limit = limit.toLong(),
                offset = offset.toLong()
            )
                .executeAsList()
                .map(CoinDaoToDomainMapper::toDomain)
                .let { CoinsPage(coins = it, offset = offset, total = total) }
    }

    override suspend fun favouriteCoins(limit: Int, offset: Int): CoinsPage {
        checkLimitAndOffset(limit = limit, offset = offset)
        val total = database.coinDaoQueries.countFavourites(isFavourite = true)
            .executeAsOneOrNull()
            ?.toInt()
            ?: 0

        return checkSize(size = total, limit = limit, offset = offset) ?:
            database.coinDaoQueries.selectFavourites(
                isFavourite = true,
                limit = limit.toLong(),
                offset = offset.toLong()
            )
                .executeAsList()
                .map(CoinDaoToDomainMapper::toDomain)
                .let { CoinsPage(coins = it, offset = offset, total = total) }
    }

    override suspend fun search(searchTerm: String, limit: Int, offset: Int): CoinsPage {
        checkLimitAndOffset(limit = limit, offset = offset)
        val total = database.coinDaoQueries.countSearch(
            symbol = searchTerm,
            name = searchTerm
        )
            .executeAsOneOrNull()
            ?.toInt()
            ?: 0

        return checkSize(size = total, limit = limit, offset = offset) ?:
            database.coinDaoQueries.search(
                symbol = searchTerm,
                name = searchTerm,
                limit = limit.toLong(),
                offset = offset.toLong()
            )
                .executeAsList()
                .map(CoinDaoToDomainMapper::toDomain)
                .let { CoinsPage(coins = it, offset = offset, total = total) }
    }

    override suspend fun append(coins: List<PricedCoin>) {
        database.coinDaoQueries.transaction {
            coins.forEach { coin ->
                database.coinDaoQueries.insert(
                    CoinDaoToDomainMapper.fromDomain(coin)
                )
            }
        }
    }

    override suspend fun deleteAll() {
        database.coinDaoQueries.deleteAll()
    }
}
