package ru.mishbanya.vsuweather.domain.repository

import kotlinx.coroutines.flow.StateFlow
import ru.mishbanya.vsuweather.data.dto.CityWeather

interface WeatherCommon {
    val cities: StateFlow<List<CityWeather>>

    suspend fun addCity(name: String)
    suspend fun removeCity(cityWeather: CityWeather)
    suspend fun startCity(cityWeather: CityWeather)
    suspend fun updateWeather()
}