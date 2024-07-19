package com.orcchg.crypto.sample.mobileapp.data.source.local.backend.adapter

import app.cash.sqldelight.ColumnAdapter

object StringStringAdapter : ColumnAdapter<String, String> {

    override fun decode(databaseValue: String): String = databaseValue

    override fun encode(value: String): String = value
}
