package com.orcchg.crypto.sample.mobileapp.data.source.remote

import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal interface CoinsRemoteFacade {
    suspend fun coins(offset: Int, limit: Int): CoinsPage
}
