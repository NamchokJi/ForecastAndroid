package com.namchok.forecast

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class ForecastApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        initKoin()
    }

    private fun initKoin() {
        startKoin {
            androidContext(this@ForecastApplication)
            androidLogger()
            modules(appModule)
        }
    }
}
