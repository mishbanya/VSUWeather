package ru.mishbanya.vsuweather.data.common

import android.content.Context
import android.content.SharedPreferences

object WeatherSharedPreferences {
    const val KEY = "vsu_weather_key"

    lateinit var preferences: SharedPreferences
        private set

    fun init(context: Context){
        preferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE)
    }
}