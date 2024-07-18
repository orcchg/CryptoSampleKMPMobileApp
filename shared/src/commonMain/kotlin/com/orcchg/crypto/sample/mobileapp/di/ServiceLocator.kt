package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import kotlinx.coroutines.flow.MutableStateFlow

interface ServiceLocator {
    val searchTerm: MutableStateFlow<String>
        get() = MutableStateFlow("")

    fun cryptoRepository(results: CoinListResults): CryptoRepository
}
