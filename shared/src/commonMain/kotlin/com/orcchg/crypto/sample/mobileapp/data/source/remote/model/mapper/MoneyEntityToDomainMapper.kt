package com.orcchg.crypto.sample.mobileapp.data.source.remote.model.mapper

import com.orcchg.crypto.sample.mobileapp.data.source.remote.model.MoneyEntity
import com.orcchg.crypto.sample.mobileapp.domain.model.Money
import com.orcchg.crypto.sample.mobileapp.domain.model.MoneySign

internal object MoneyEntityToDomainMapper {
    fun toDomain(entity: MoneyEntity): Money =
        with (entity) {
            Money(
                amount = amount,
                currency = currency,
                sign = MoneySign.from(sign)
            )
        }
}
