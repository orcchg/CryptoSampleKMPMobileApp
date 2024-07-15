package com.orcchg.crypto.sample.mobileapp.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PricedCoinEntity(
    @SerialName("coin") val coin: CoinEntity,
    @SerialName("price") val price: MoneyEntity,
    @SerialName("delta") val delta: MoneyEntity
)
