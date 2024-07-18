package com.orcchg.crypto.sample.mobileapp.di.modules

import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.InMemoryCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.RealCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.RealCoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
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
