package com.orcchg.crypto.sample.mobileapp.data

import com.orcchg.crypto.sample.mobileapp.domain.CryptoRepository
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal class RealCryptoRepository : CryptoRepository {

    override suspend fun coins(limit: Int, offset: Int): CoinsPage {
        TODO("Not yet implemented")
    }
}
