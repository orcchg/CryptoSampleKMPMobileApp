package com.orcchg.crypto.sample.mobileapp.presentation.model.mapper

import com.orcchg.crypto.sample.mobileapp.domain.model.MoneySign
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin
import com.orcchg.crypto.sample.mobileapp.presentation.model.CoinVo
import com.orcchg.crypto.sample.mobileapp.presentation.model.DeltaSign

internal object PricedCoinToCoinVoMapper {
    fun toVo(coin: PricedCoin): CoinVo =
        with (coin.coin) {
            val deltaSign = when (coin.delta.sign) {
                MoneySign.MINUS -> DeltaSign.MINUS
                MoneySign.PLUS -> DeltaSign.PLUS
            }
                .let {
                    if (coin.delta.isZero()) {
                        DeltaSign.NONE
                    } else it
                }

            val delta = "${deltaSign.ch}${coin.delta} (${coin.deltaPercentage}%)"

            CoinVo(
                index = index,
                name = name,
                symbol = symbol,
                url = url,
                logoUrl = logoUrl,
                price = coin.price.toString(),
                delta = delta,
                deltaSign = deltaSign,
                isFavourite = isFavourite
            )
        }
}
