package com.orcchg.crypto.sample.mobileapp.di.modules

import com.orcchg.crypto.sample.mobileapp.data.repository.LocalCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.repository.PersistentLocalCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.repository.PersistentRealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.repository.RealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.RepositoryQualifier
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

/**
 * Uses persistent cache.
 */
internal val cryptoRepositoryModule = module {
    includes(
        localDataSourceModule,
        remoteDataSourceModule
    )

    single<CryptoRepository>(named(RepositoryQualifier.LOCAL)) {
        LocalCryptoRepository(
            local = get(qualifier = named(CacheQualifier.PERSISTENT))
        )
    }
    single<CryptoRepository>(named(RepositoryQualifier.REAL)) {
        RealCryptoRepository(
            local = get(qualifier = named(CacheQualifier.PERSISTENT)),
            remote = get()
        )
    }
}

/**
 * Uses in-memory cache.
 */
internal val cryptoRepositoryInMemoryModule = module {
    includes(
        localDataSourceModule,
        remoteDataSourceModule
    )

    single<CryptoRepository>(named(RepositoryQualifier.LOCAL)) {
        LocalCryptoRepository(
            local = get(qualifier = named(CacheQualifier.IN_MEMORY))
        )
    }
    single<CryptoRepository>(named(RepositoryQualifier.REAL)) {
        RealCryptoRepository(
            local = get(qualifier = named(CacheQualifier.IN_MEMORY)),
            remote = get()
        )
    }
}

/**
 * Uses persistent cache and sqldelight paging binding.
 */
internal val cryptoRepositoryPersistentModule = module {
    includes(
        localDataSourceModule,
        remoteDataSourceModule
    )

    single<CryptoRepository>(named(RepositoryQualifier.PERSISTENT_LOCAL)) {
        PersistentLocalCryptoRepository(database = get())
    }
    single<CryptoRepository>(named(RepositoryQualifier.PERSISTENT_REAL)) {
        PersistentRealCryptoRepository(
            database = get(),
            local = get(qualifier = named(CacheQualifier.PERSISTENT)),
            remote = get()
        )
    }
}
