package com.orcchg.crypto.sample.mobileapp.domain.model

data class CoinsPage(
    val coins: List<PricedCoin>,
    val offset: Int,
    val total: Int
)
