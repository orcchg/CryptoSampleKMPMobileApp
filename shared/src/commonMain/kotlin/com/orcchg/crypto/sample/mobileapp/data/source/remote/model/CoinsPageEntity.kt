package com.orcchg.crypto.sample.mobileapp.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinsPageEntity(
    @SerialName("coins") val coins: List<PricedCoinEntity> = emptyList(),
    @SerialName("offset") val offset: Int,
    @SerialName("total") val total: Int
)
