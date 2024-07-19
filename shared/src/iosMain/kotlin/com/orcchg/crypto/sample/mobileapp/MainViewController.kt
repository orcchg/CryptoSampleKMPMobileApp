package com.orcchg.crypto.sample.mobileapp

import androidx.compose.ui.window.ComposeUIViewController
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.DriverFactory
import com.orcchg.crypto.sample.mobileapp.di.modules.databaseModule
import com.orcchg.crypto.sample.mobileapp.di.modules.serviceLocatorModule
import com.orcchg.crypto.sample.mobileapp.presentation.screen.MainScreen
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named

fun MainViewController() = ComposeUIViewController {
    val koin = startKoin {
        modules(
            databaseModule(DriverFactory()),
            serviceLocatorModule
        )
    }
    MainScreen(serviceLocator = koin.koin.get(qualifier = named(CacheQualifier.IN_MEMORY)))
}
