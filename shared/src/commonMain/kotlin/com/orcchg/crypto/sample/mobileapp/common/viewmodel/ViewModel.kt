package com.orcchg.crypto.sample.mobileapp.common.viewmodel

import kotlinx.coroutines.CoroutineScope

@Suppress("EmptyDefaultConstructor")
expect open class ViewModel() {
    val viewModelScope: CoroutineScope

    open fun onCleared()
}
