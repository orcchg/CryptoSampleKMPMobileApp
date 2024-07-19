package com.orcchg.crypto.sample.mobileapp

import android.app.Application
import com.orcchg.crypto.sample.mobileapp.data.source.local.backend.DriverFactory
import com.orcchg.crypto.sample.mobileapp.di.modules.databaseModule
import com.orcchg.crypto.sample.mobileapp.di.modules.serviceLocatorModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AndroidApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AndroidApp)
            androidLogger()
            modules(
                databaseModule(DriverFactory(applicationContext)),
                serviceLocatorModule
            )
        }
    }
}
