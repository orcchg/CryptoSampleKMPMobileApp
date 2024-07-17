package com.orcchg.crypto.sample.mobileapp.data.source.remote.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.PricedCoinEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal object PricedCoinEntityToDomainMapper {
    fun toDomain(entity: PricedCoinEntity): PricedCoin =
        with (entity) {
            PricedCoin(
                coin = CoinEntityToDomainMapper.toDomain(coin),
                price = MoneyEntityToDomainMapper.toDomain(price),
                delta = MoneyEntityToDomainMapper.toDomain(delta)
            )
        }
}
