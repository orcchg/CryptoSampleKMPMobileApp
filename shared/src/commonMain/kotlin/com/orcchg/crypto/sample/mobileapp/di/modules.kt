package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.data.repository.LocalCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.repository.PersistentLocalCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.repository.PersistentRealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.repository.RealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.RepositoryQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.InMemoryCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.RealCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.RealCoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal fun databaseModule(database: CryptoSampleKMPDatabase) = module {
    single { database }
}

internal val localDataSourceModule = module {
    single<CoinsDatabaseFacade>(named(CacheQualifier.IN_MEMORY)) {
        InMemoryCoinsDatabaseFacade()
    }

    single<CoinsDatabaseFacade>(named(CacheQualifier.PERSISTENT)) {
        RealCoinsDatabaseFacade(database = get())
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
    single<CryptoRepository>(named(RepositoryQualifier.LOCAL)) {
        LocalCryptoRepository(
            local = get(qualifier = named(CacheQualifier.IN_MEMORY))
        )
    }
    single<CryptoRepository>(named(RepositoryQualifier.PERSISTENT_LOCAL)) {
        PersistentLocalCryptoRepository(database = get())
    }
    single<CryptoRepository>(named(RepositoryQualifier.REAL)) {
        RealCryptoRepository(
            local = get(qualifier = named(CacheQualifier.IN_MEMORY)),
            remote = get()
        )
    }
    single<CryptoRepository>(named(RepositoryQualifier.PERSISTENT_REAL)) {
        PersistentRealCryptoRepository(
            database = get(),
            local = get(qualifier = named(CacheQualifier.PERSISTENT)),
            remote = get()
        )
    }
    single<ServiceLocator> {
        RealServiceLocator(
            cryptoRepository = get(),
            localCryptoRepository = get()
        )
    }
}
