package com.orcchg.crypto.sample.mobileapp.domain.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.CoinsPageEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal object CoinsPageToDomainMapper {
    fun map(entity: CoinsPageEntity): CoinsPage =
        with (entity) {
            CoinsPage(
                coins = coins.map(PricedCoinEntityToDomainMapper::map),
                offset = offset,
                total = total
            )
        }
}
