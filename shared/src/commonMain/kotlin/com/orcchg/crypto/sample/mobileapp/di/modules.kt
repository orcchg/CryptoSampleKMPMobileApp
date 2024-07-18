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


