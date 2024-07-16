package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.data.repository.RealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.Qualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.InMemoryCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.RealCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.RealCoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import org.koin.core.annotation.KoinInternalApi
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val localDataSourceModule = module {
    single<CoinsDatabaseFacade>(named(Qualifier.IN_MEMORY)) {
        InMemoryCoinsDatabaseFacade()
    }

    single<CoinsDatabaseFacade>(named(Qualifier.PERSISTENT)) {
        RealCoinsDatabaseFacade()
    }
}

internal val remoteDataSourceModule = module {
    single { BackendPreferences() }
    single<CoinsRemoteFacade> {
        RealCoinsRemoteFacade(backendPreferences = get())
    }
}

internal val serviceLocatorModule = module {
    includes(
        localDataSourceModule,
        remoteDataSourceModule
    )
    single<CryptoRepository> {
        RealCryptoRepository(
            local = get(qualifier = named(Qualifier.IN_MEMORY)),
            remote = get()
        )
    }
    single<ServiceLocator> {
        RealServiceLocator(cryptoRepository = get())
    }
}
