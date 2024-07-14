package com.orcchg.crypto.sample.mobileapp.domain

import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

interface CryptoRepository {
    suspend fun coins(limit: Int, offset: Int): CoinsPage
}
