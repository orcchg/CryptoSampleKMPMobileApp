package com.orcchg.crypto.sample.mobileapp.presentation.model

import androidx.compose.runtime.Stable

@Stable
internal class CoinVo(
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

internal enum class DeltaSign(val ch: Char) {
    MINUS('-'),
    PLUS('+'),
    NONE(' ')
}
