package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository

interface ServiceLocator {
    fun cryptoRepository(results: CoinListResults): CryptoRepository
}
