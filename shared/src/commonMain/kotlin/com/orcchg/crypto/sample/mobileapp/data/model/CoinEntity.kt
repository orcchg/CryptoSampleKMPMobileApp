package com.orcchg.crypto.sample.mobileapp.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoinEntity(
    @SerialName("index") val index: Long,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("url") val url: String,
    @SerialName("logoUrl") val logoUrl: String
)
