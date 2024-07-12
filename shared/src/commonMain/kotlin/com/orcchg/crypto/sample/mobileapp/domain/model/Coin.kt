package com.orcchg.crypto.sample.mobileapp.domain.model

class Coin(
    val index: Long,
    val name: String,
    val symbol: String,
    val url: String,
    val logoUrl: String,
    val price: String,
    val delta: String,
    val deltaSign: DeltaSign = DeltaSign.NONE,
    var isFavourite: Boolean = false
)

enum class DeltaSign { MINUS, PLUS, NONE }
