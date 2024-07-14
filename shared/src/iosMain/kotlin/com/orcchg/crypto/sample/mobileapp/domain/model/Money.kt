package com.orcchg.crypto.sample.mobileapp.domain.model

actual class Money(
    actual val sign: MoneySign
) {
    actual constructor(): this(sign = MoneySign.PLUS)
    actual constructor(
        amount: String,
        currency: String,
        sign: MoneySign
    ): this(sign)

    actual operator fun plus(other: Money): Money = Money()
    actual operator fun minus(other: Money): Money = Money()
    actual operator fun div(other: Money): Money = Money()
    actual operator fun times(other: Money): Money = Money()
    actual fun isZero(): Boolean = false
}

actual fun Double.money(): Money = Money()
