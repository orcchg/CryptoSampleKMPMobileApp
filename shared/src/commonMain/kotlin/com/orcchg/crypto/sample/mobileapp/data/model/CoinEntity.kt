package com.orcchg.crypto.sample.mobileapp.data.model

import com.orcchg.crypto.sample.mobileapp.domain.model.Coin
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class CoinEntity(
    @SerialName("index") val index: Long,
    @SerialName("name") val name: String,
    @SerialName("symbol") val symbol: String,
    @SerialName("url") val url: String,
    @SerialName("logoUrl") val logoUrl: String
) {
    fun toDomain(): Coin =
        Coin(
            index = index,
            name = name,
            symbol = symbol,
            url = url,
            logoUrl = logoUrl
        )
}
