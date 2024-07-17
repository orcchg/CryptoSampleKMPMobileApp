package com.orcchg.crypto.sample.mobileapp.data.source.remote.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.CoinEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin

internal object CoinEntityToDomainMapper {
    fun toDomain(entity: CoinEntity): Coin =
        with (entity) {
            Coin(
                index = index,
                name = name,
                symbol = symbol,
                url = url,
                logoUrl = logoUrl,
                isFavourite = false // no favourite status in remote
            )
        }
}
