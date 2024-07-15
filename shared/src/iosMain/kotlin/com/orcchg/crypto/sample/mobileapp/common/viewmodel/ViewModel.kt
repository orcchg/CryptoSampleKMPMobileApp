package com.orcchg.crypto.sample.mobileapp.common.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

@Suppress("EmptyDefaultConstructor")
actual open class ViewModel actual constructor() {
    actual val viewModelScope: CoroutineScope = createViewModelScope()

    actual open fun onCleared() {
        viewModelScope.cancel()
    }
}
