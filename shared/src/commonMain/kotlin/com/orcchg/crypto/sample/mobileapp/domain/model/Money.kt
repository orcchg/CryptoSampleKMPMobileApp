package com.orcchg.crypto.sample.mobileapp.domain.model

enum class MoneySign(val ch: Char) {
    MINUS('-'), PLUS('+');

    companion object {
        fun from(str: String): MoneySign =
            when (str) {
                "-" -> MINUS
                "+" -> PLUS
                else -> throw IllegalArgumentException("Unrecognized sign: $str")
            }
    }
}

expect class Money {
    val sign: MoneySign

    constructor()
    constructor(
        amount: String,
        currency: String,
        sign: MoneySign
    )

    operator fun plus(other: Money): Money
    operator fun minus(other: Money): Money
    operator fun div(other: Money): Money
    operator fun times(other: Money): Money
    fun isZero(): Boolean
    fun getAmountAsString(): String
    fun getCurrencyAsString(): String
}

expect fun Double.money(): Money
