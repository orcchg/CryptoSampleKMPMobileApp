package com.orcchg.crypto.sample.mobileapp.di.modules

import app.cash.sqldelight.EnumColumnAdapter
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.DriverFactory
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.InMemoryCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.RealCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.adapter.LongLongAdapter
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.adapter.StringStringAdapter
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.fake.FakeCoinsDatabaseFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.CoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.BackendPreferences
import com.orcchg.crypto.sample.mobileapp.data.source.remote.backend.RealCoinsRemoteFacade
import com.orcchg.crypto.sample.mobileapp.database.CoinDao
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase
import com.orcchg.crypto.sample.mobileapp.domain.model.MoneySign
import org.koin.core.qualifier.named
import org.koin.dsl.module


fun databaseModule(driverFactory: DriverFactory) = module {
    single {
        CryptoSampleKMPDatabase(
            driverFactory.driver,
            CoinDaoAdapter = CoinDao.Adapter(
                idAdapter = LongLongAdapter,
                nameAdapter = StringStringAdapter,
                symbolAdapter = StringStringAdapter,
                priceSignAdapter = EnumColumnAdapter<MoneySign>(),
                deltaSignAdapter = EnumColumnAdapter<MoneySign>(),
                createdAtAdapter = LongLongAdapter
            )
        )
    }
}

internal val localDataSourceModule = module {
    single<CoinsDatabaseFacade>(named(CacheQualifier.FAKE)) {
        FakeCoinsDatabaseFacade(
            facade = get(qualifier = named(CacheQualifier.IN_MEMORY))
        )
    }

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
