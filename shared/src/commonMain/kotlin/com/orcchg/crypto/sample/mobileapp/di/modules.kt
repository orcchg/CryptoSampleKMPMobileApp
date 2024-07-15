package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.data.repository.RealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import org.koin.dsl.module

val serviceLocatorModule = module {
    single { BackendPreferences() }
    single<CryptoRepository> {
        RealCryptoRepository(
            backendPreferences = get()
        )
    }
    single<ServiceLocator> {
        RealServiceLocator(
            cryptoRepository = get()
        )
    }
}
