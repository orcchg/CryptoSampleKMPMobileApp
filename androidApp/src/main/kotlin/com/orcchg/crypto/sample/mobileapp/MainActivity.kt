package com.orcchg.crypto.sample.mobileapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.orcchg.crypto.sample.mobileapp.data.source.local.CacheQualifier
import com.orcchg.crypto.sample.mobileapp.di.ServiceLocator
import com.orcchg.crypto.sample.mobileapp.presentation.screen.MainScreen
import org.koin.android.ext.android.inject
import org.koin.core.qualifier.named

class MainActivity : ComponentActivity() {
    private val serviceLocator: ServiceLocator by inject(
        qualifier = named(CacheQualifier.IN_MEMORY),
        mode = LazyThreadSafetyMode.NONE
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MainScreen(serviceLocator)
        }
    }
}
