package com.orcchg.crypto.sample.mobileapp.common.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel

@Suppress("EmptyDefaultConstructor")
actual open class ViewModel actual constructor() : ViewModel() {
    actual val viewModelScope: CoroutineScope = createViewModelScope()

    public actual override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }
}
