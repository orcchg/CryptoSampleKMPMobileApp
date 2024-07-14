package com.orcchg.crypto.sample.mobileapp.domain.model

data class PricedCoin(
    val coin: Coin,
    val price: Money,
    val delta: Money
) {
    val deltaPercentage = delta / price * 100.0.money()
}
