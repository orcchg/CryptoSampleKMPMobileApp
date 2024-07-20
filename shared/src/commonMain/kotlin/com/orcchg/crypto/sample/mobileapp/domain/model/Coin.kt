package com.orcchg.crypto.sample.mobileapp.domain.model

data class Coin(
    val index: Long,
    val name: String,
    val symbol: String,
    val url: String,
    val logoUrl: String,
    val isFavourite: Boolean
)
