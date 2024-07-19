package com.orcchg.crypto.sample.mobileapp.domain.repository

import app.cash.paging.PagingData
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import kotlinx.coroutines.flow.Flow

interface CryptoRepository {
    val coinsPages: Flow<PagingData<PricedCoin>>

    suspend fun setFavourite(coinIndex: Long, isFavourite: Boolean)
}
