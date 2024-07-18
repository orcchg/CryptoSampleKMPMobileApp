package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.data.repository.RealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.FavouritesPersistentCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.data.source.local.PersistentCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.data.source.local.SearchPersistentCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import org.koin.core.Koin
import org.koin.core.qualifier.named

internal class PersistentServiceLocator(
    private val koin: Koin
) : ServiceLocator {

    override fun cryptoRepository(results: CoinListResults): CryptoRepository =
        when (results) {
            CoinListResults.ALL ->
                RealCryptoRepository(
                    local = koin.get(qualifier = named(CacheQualifier.PERSISTENT)),
                    remote = koin.get(),
                    useAsLocalOnlyDataSource = false,
                    pagingSourceFactory = {
                        PersistentCoinsPagingLocalSource(
                            database = koin.get()
                        )
                    }
                )
            CoinListResults.FAVOURITE ->
                RealCryptoRepository(
                    local = koin.get(qualifier = named(CacheQualifier.PERSISTENT)),
                    remote = koin.get(),
                    useAsLocalOnlyDataSource = true,
                    pagingSourceFactory = {
                        FavouritesPersistentCoinsPagingLocalSource(
                            database = koin.get()
                        )
                    }
                )
            CoinListResults.SEARCH ->
                RealCryptoRepository(
                    local = koin.get(qualifier = named(CacheQualifier.PERSISTENT)),
                    remote = koin.get(),
                    useAsLocalOnlyDataSource = true,
                    pagingSourceFactory = {
                        SearchPersistentCoinsPagingLocalSource(
                            database = koin.get(),
                            searchTerm = searchTerm.value
                        )
                    }
                )
        }
}
