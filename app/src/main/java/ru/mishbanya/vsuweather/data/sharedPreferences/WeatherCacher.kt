package ru.mishbanya.vsuweather.data.sharedPreferences

import ru.mishbanya.vsuweather.data.sharedPreferences.dto.LocalCityModel

interface WeatherCacher {
    suspend fun getWeatherState(): List<LocalCityModel>
    suspend fun saveWeatherState(list: List<LocalCityModel>)
}