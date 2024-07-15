package com.orcchg.crypto.sample.mobileapp.domain.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.PricedCoinEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal object PricedCoinEntityToDomainMapper {
    fun map(entity: PricedCoinEntity): PricedCoin =
        with (entity) {
            PricedCoin(
                coin = CoinEntityToDomainMapper.map(coin),
                price = MoneyEntityToDomainMapper.map(price),
                delta = MoneyEntityToDomainMapper.map(delta)
            )
        }
}
