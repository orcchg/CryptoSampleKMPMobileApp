package com.orcchg.crypto.sample.mobileapp.common

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO

actual fun ioDispatcher(): CoroutineDispatcher = Dispatchers.IO
