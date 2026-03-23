package ru.mishbanya.vsuweather.domain.repository

import ru.mishbanya.vsuweather.data.dto.CityWeather

interface WeatherCacher {
    suspend fun saveWeatherState(list: List<CityWeather>)
    suspend fun getWeatherState(): List<CityWeather>
}