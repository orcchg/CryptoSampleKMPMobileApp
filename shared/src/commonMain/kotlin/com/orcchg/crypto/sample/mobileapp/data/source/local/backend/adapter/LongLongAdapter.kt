package com.orcchg.crypto.sample.mobileapp.data.source.local.backend.adapter

import app.cash.sqldelight.ColumnAdapter

object LongLongAdapter : ColumnAdapter<Long, Long> {

    override fun decode(databaseValue: Long): Long = databaseValue

    override fun encode(value: Long): Long = value
}
