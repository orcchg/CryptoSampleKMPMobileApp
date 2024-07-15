package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal class RealCoinsDatabaseFacade : CoinsDatabaseFacade {
    override suspend fun isEmptyOrExpired(): Boolean {
        TODO("Not yet implemented")
    }

    override suspend fun coins(offset: Int, limit: Int): CoinsPage {
        TODO("Not yet implemented")
    }

    override suspend fun append(coins: List<PricedCoin>) {
        TODO("Not yet implemented")
    }

    override suspend fun deleteAll() {
        TODO("Not yet implemented")
    }
}
