package ru.mishbanya.vsuweather.data.repository

import ru.mishbanya.vsuweather.data.dto.Weather

interface WeatherGetter {
    fun getWeather(name: String): Weather
}