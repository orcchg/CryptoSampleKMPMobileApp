package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.data.Constants
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import kotlinx.datetime.Clock
import kotlin.math.abs

internal class RealCoinsDatabaseFacade(
    private val database: CryptoSampleKMPDatabase
) : CoinsDatabaseFacade {

    override suspend fun isEmptyOrExpired(): Boolean =
        (database.coinDaoQueries.count().executeAsOneOrNull() ?: 0) <= 0 ||
        abs(Clock.System.now().toEpochMilliseconds() - (database.coinDaoQueries.minByCreatedAt().executeAsOneOrNull()?.createdAt ?: 0L)) >= Constants.CACHE_EXPIRATION_MILLIS

    override suspend fun coins(offset: Int, limit: Int): CoinsPage =
        retrieve(
            items = database.coinDaoQueries.select().executeAsList(),
            offset = offset,
            limit = limit
        )

    override suspend fun favouriteCoins(offset: Int, limit: Int): CoinsPage =
        retrieve(
            items = database.coinDaoQueries.selectFavourites(isFavourite = true).executeAsList(),
            offset = offset,
            limit = limit
        )

    override suspend fun search(searchTerm: String, offset: Int, limit: Int): CoinsPage =
        retrieve(
            items = database.coinDaoQueries.search(
                symbol = searchTerm,
                name = searchTerm
            )
                .executeAsList(),
            offset = offset,
            limit = limit
        )

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
