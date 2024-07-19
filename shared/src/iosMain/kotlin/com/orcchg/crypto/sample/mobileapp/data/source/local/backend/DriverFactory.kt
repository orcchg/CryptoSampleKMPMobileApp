package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase

actual class DriverFactory {
    actual val driver: SqlDriver =
        NativeSqliteDriver(
            schema = CryptoSampleKMPDatabase.Schema,
            name = "CryptoSampleKMPDatabase.db"
        )
}
