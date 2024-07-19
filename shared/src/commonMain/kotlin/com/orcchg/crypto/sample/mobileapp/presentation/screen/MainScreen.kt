package com.orcchg.crypto.sample.mobileapp.presentation.screen

import androidx.compose.runtime.Composable
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.theme.ApplicationTheme

@Composable
fun MainScreen(serviceLocator: ServiceLocator) {
    ApplicationTheme {
        SearchHostScreen(serviceLocator)
    }
}
