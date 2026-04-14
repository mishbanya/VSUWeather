package ru.mishbanya.vsuweather

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.ksp.generated.module

class VSUWeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@VSUWeatherApplication)
            modules(VSUWeatherModule().module)
        }
    }
}