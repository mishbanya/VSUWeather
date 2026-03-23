package ru.mishbanya.vsuweather

import android.app.Application
import ru.mishbanya.vsuweather.data.common.WeatherSharedPreferences

class VSUWeatherApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        WeatherSharedPreferences.init(applicationContext)
    }
}