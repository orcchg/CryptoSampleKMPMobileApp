package com.orcchg.crypto.sample.mobileapp.data.source.remote.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.CoinsPageEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal object CoinsPageToDomainMapper {
    fun toDomain(entity: CoinsPageEntity): CoinsPage =
        with (entity) {
            CoinsPage(
                coins = coins.map(PricedCoinEntityToDomainMapper::toDomain),
                offset = offset,
                total = total
            )
        }
}
