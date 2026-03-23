package ru.mishbanya.vsuweather.domain.repository

import ru.mishbanya.vsuweather.data.dto.Weather
import java.time.LocalDate

interface WeatherGetter {
    suspend fun getWeather(name: String, date: LocalDate): Weather
}