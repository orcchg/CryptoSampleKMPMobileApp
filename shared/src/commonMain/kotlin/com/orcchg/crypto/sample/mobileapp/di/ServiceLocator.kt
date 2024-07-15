package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository

interface ServiceLocator {
    val cryptoRepository: CryptoRepository
}
