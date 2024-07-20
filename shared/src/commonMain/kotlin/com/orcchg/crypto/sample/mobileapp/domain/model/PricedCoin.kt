package com.orcchg.crypto.sample.mobileapp.domain.model

data class PricedCoin(
    val coin: Coin,
    val price: Money,
    val delta: Money
) {
    val deltaPercentage = try {
        delta / price * 100.0.money()
    } catch (e: Throwable) {
        // Logger.e(e) { "coin: $coin ($price, $delta)" }
        0.0
    }
}
