package com.orcchg.crypto.sample.mobileapp.domain.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.model.MoneyEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.Money
import com.orcchg.crypto.sample.mobileapp.domain.model.MoneySign

internal object MoneyEntityToDomainMapper {
    fun map(entity: MoneyEntity): Money =
        with (entity) {
            Money(
                amount = amount,
                currency = currency,
                sign = MoneySign.from(sign)
            )
        }
}
