package com.orcchg.crypto.sample.mobileapp.di.modules

import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.di.PersistentServiceLocator
import com.orcchg.crypto.sample.mobileapp.di.RealServiceLocator
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import org.koin.core.qualifier.named
import org.koin.dsl.module

val serviceLocatorModule = module {
    includes(
        localDataSourceModule,
        remoteDataSourceModule
    )

    single<ServiceLocator>(named(CacheQualifier.FAKE)) {
        RealServiceLocator(koin = getKoin(), cacheQualifier = CacheQualifier.FAKE)
    }
    single<ServiceLocator>(named(CacheQualifier.IN_MEMORY)) {
        RealServiceLocator(koin = getKoin(), cacheQualifier = CacheQualifier.IN_MEMORY)
    }
    single<ServiceLocator>(named(CacheQualifier.PERSISTENT)) {
        RealServiceLocator(koin = getKoin(), cacheQualifier = CacheQualifier.PERSISTENT)
    }
}

val serviceLocatorPersistentModule = module {
    includes(
        localDataSourceModule,
        remoteDataSourceModule
    )

    single<ServiceLocator> {
        PersistentServiceLocator(koin = getKoin())
    }
}
