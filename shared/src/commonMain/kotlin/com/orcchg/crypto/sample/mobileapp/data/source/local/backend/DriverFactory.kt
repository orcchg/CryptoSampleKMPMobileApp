package com.orcchg.crypto.sample.mobileapp.data.source.local.backend

import app.cash.sqldelight.db.SqlDriver

expect class DriverFactory {
    val driver: SqlDriver
}
