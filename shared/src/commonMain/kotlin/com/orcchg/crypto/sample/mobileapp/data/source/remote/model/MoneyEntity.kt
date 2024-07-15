package com.orcchg.crypto.sample.mobileapp.data.source.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MoneyEntity(
    @SerialName("amount") val amount: String,
    @SerialName("currency") val currency: String,
    @SerialName("sign") val sign: String
)
