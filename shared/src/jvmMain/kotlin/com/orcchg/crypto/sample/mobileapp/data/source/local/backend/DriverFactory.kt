package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.jdbc.sqlite.JdbcSqliteDriver
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase

actual class DriverFactory {
    actual val driver: SqlDriver = run {
        val driver: SqlDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        CryptoSampleKMPDatabase.Schema.create(driver)
        driver
    }
}
