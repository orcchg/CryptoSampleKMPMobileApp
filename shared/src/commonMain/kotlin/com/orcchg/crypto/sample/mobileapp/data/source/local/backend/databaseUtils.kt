package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import com.orcchg.crypto.sample.mobileapp.domain.model.CoinsPage

internal fun checkLimitAndOffset(limit: Int, offset: Int) {
    if (limit < 0 || offset < 0) {
        throw IllegalArgumentException("limit $limit and offset $offset must not be negative")
    }

}

internal fun checkSize(size: Int, limit: Int, offset: Int): CoinsPage? =
    if (size == 0 || limit == 0 || offset >= size) {
        CoinsPage(
            coins = emptyList(),
            offset = offset,
            total = 0
        )
    } else null
