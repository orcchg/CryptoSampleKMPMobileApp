package com.orcchg.crypto.sample.mobileapp.di

import com.orcchg.crypto.sample.mobileapp.data.repository.RealCryptoRepository
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.CoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.data.source.local.FavouritesCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.data.source.local.SearchCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.domain.CoinListResults
import com.orcchg.crypto.sample.mobileapp.domain.repository.CryptoRepository
import org.koin.core.Koin
import org.koin.core.qualifier.named

internal class RealServiceLocator(
    private val koin: Koin,
    private val cacheQualifier: CacheQualifier
) : ServiceLocator {

    override fun cryptoRepository(results: CoinListResults): CryptoRepository =
        when (results) {
            CoinListResults.ALL ->
                RealCryptoRepository(
                    local = koin.get(qualifier = named(cacheQualifier)),
                    remote = koin.get(),
                    useAsLocalOnlyDataSource = false,
                    pagingSourceFactory = {
                        CoinsPagingLocalSource(
                            local = koin.get(qualifier = named(cacheQualifier))
                        )
                    }
                )
            CoinListResults.FAVOURITE ->
                RealCryptoRepository(
                    local = koin.get(qualifier = named(cacheQualifier)),
                    remote = koin.get(),
                    useAsLocalOnlyDataSource = true,
                    pagingSourceFactory = {
                        FavouritesCoinsPagingLocalSource(
                            local = koin.get(qualifier = named(cacheQualifier))
                        )
                    }
                )
            CoinListResults.SEARCH ->
                RealCryptoRepository(
                    local = koin.get(qualifier = named(cacheQualifier)),
                    remote = koin.get(),
                    useAsLocalOnlyDataSource = true,
                    pagingSourceFactory = {
                        SearchCoinsPagingLocalSource(
                            local = koin.get(qualifier = named(cacheQualifier)),
                            searchTerm = searchTerm.value
                        )
                    }
                )
        }
}
