package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import android.content.Context
import androidx.sqlite.db.SupportSQLiteDatabase
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver
import com.orcchg.crypto.sample.mobileapp.database.CryptoSampleKMPDatabase

actual class DriverFactory(context: Context) {
    actual val driver: SqlDriver by lazy(LazyThreadSafetyMode.NONE) {
        AndroidSqliteDriver(
            schema = CryptoSampleKMPDatabase.Schema,
            context = context,
            name = "CryptoSampleKMPDatabase.db",
            cacheSize = 100,
            callback = object : AndroidSqliteDriver.Callback(CryptoSampleKMPDatabase.Schema) {
                override fun onDowngrade(
                    db: SupportSQLiteDatabase,
                    oldVersion: Int,
                    newVersion: Int,
                ) {
                    db.version = maxOf(oldVersion, newVersion)
                }
            }
        )
    }
}
