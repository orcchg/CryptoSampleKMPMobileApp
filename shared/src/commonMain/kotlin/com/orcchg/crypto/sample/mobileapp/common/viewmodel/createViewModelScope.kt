package com.orcchg.crypto.sample.mobileapp.common.viewmodel

import kotlinx.coroutines.CoroutineScope
import kotlin.native.concurrent.ThreadLocal

/**
 * Factory of viewModelScope. Internal API, for ability of tests to change viewModelScope
 * dispatcher.
 *
 * By default, implementation creates main-thread dispatcher scope.
 */
@ThreadLocal
var createViewModelScope: () -> CoroutineScope = {
    CoroutineScope(createUIDispatcher())
}
