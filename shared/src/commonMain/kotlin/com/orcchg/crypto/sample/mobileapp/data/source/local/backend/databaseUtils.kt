package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.data.source.local.model.mapper.CoinDaoToDomainMapper
import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal fun checkLimitAndOffset(offset: Int, limit: Int) {
    if (limit < 0 || offset < 0) {
        throw IllegalArgumentException("limit $limit and offset $offset must not be negative")
    }
}

internal fun retrieve(items: List<CoinDao>, offset: Int, limit: Int): CoinsPage {
    checkLimitAndOffset(offset = offset, limit = limit)
    if (items.isEmpty() || limit == 0 || offset >= items.size) {
        return CoinsPage(
            coins = emptyList(),
            offset = offset,
            total = items.size
        )
    }
    val coins = items.subList(offset, (offset + limit).coerceAtMost(items.size))
    return CoinsPage(
        coins = coins.map(CoinDaoToDomainMapper::toDomain),
        offset = offset,
        total = items.size
    )
}
