package com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper

import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.domain.model.Coin
import com.orcchg.crypto.sample.mobileapp.domain.model.Money
import com.orcchg.crypto.sample.mobileapp.domain.model.MoneySign
import com.orcchg.crypto.sample.mobileapp.domain.model.PricedCoin

internal object CoinDaoToDomainMapper {
    fun toDomain(dao: CoinDao): PricedCoin =
        with (dao) {
            PricedCoin(
                coin = Coin(
                    index = id,
                    name = name,
                    symbol = symbol,
                    url = url.orEmpty(),
                    logoUrl = logoUrl.orEmpty(),
                    isFavourite = isFavourite
                ),
                price = Money(
                    amount = price.orEmpty(),
                    currency = priceCurrency.orEmpty(),
                    sign = priceSign ?: MoneySign.PLUS
                ),
                delta = Money(
                    amount = delta.orEmpty(),
                    currency = deltaCurrency.orEmpty(),
                    sign = deltaSign ?: MoneySign.PLUS
                )
            )
        }

    fun fromDomain(coin: PricedCoin): CoinDao =
        with (coin.coin) {
            CoinDao(
                id = index,
                name = name,
                symbol = symbol,
                url = url,
                logoUrl = logoUrl,
                price = coin.price.getAmountAsString(),
                priceCurrency = coin.price.getCurrencyAsString(),
                priceSign = coin.price.sign,
                delta = coin.delta.getAmountAsString(),
                deltaCurrency = coin.delta.getCurrencyAsString(),
                deltaSign = coin.delta.sign,
                isFavourite = isFavourite
            )
        }
}
