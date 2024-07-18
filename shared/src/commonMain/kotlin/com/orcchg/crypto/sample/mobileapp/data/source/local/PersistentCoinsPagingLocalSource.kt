package com.orcchg.crypto.sample.mobileapp.data.source.local

import com.orcchg.crypto.sample.mobileapp.data.source.local.base.BasePersistentCoinsPagingLocalSource
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase

internal class PersistentCoinsPagingLocalSource(
    database: CryptoSampleKMPDatabase,
) : BasePersistentCoinsPagingLocalSource(database, database.coinDaoQueries::select)

internal class FavouritesPersistentCoinsPagingLocalSource(
    database: CryptoSampleKMPDatabase,
) : BasePersistentCoinsPagingLocalSource(database, database.coinDaoQueries::selectFavourites)
