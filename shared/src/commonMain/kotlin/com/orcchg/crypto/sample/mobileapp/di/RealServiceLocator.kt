package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository

internal class RealServiceLocator(
    override val cryptoRepository: CryptoRepository,
    override val localCryptoRepository: CryptoRepository
) : ServiceLocator
