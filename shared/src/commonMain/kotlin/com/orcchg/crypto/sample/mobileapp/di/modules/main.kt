package com.orcchg.crypto.sample.mobileapp.di.modules

import com.orcchg.crypto.sample.mobileapp.data.source.local.RepositoryQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.ServiceLocatorQualifier
import com.orcchg.crypto.sample.mobileapp.di.RealServiceLocator
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import org.koin.core.qualifier.named
import org.koin.dsl.module

internal val serviceLocatorModule = module {
    includes(
        cryptoRepositoryModule,
        cryptoRepositoryPersistentModule
    )

    single<ServiceLocator>(named(ServiceLocatorQualifier.REAL)) {
        RealServiceLocator(
            cryptoRepository = get(qualifier = named(RepositoryQualifier.REAL)),
            localCryptoRepository = get(qualifier = named(RepositoryQualifier.LOCAL))
        )
    }

    single<ServiceLocator>(named(ServiceLocatorQualifier.PERSISTENT_REAL)) {
        RealServiceLocator(
            cryptoRepository = get(qualifier = named(RepositoryQualifier.PERSISTENT_REAL)),
            localCryptoRepository = get(qualifier = named(RepositoryQualifier.PERSISTENT_LOCAL))
        )
    }
}

internal val serviceLocatorInMemoryModule = module {
    includes(cryptoRepositoryInMemoryModule)

    single<ServiceLocator>(named(ServiceLocatorQualifier.IN_MEMORY)) {
        RealServiceLocator(
            cryptoRepository = get(qualifier = named(RepositoryQualifier.REAL)),
            localCryptoRepository = get(qualifier = named(RepositoryQualifier.LOCAL))
        )
    }
}
