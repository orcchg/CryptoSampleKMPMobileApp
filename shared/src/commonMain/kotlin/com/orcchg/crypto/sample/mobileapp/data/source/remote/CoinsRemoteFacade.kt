package com.orcchg.crypto.sample.mobileapp.data.source.remote

import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal interface CoinsRemoteFacade {
    suspend fun coins(limit: Int, offset: Int): CoinsPage
}
