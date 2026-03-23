package ru.mishbanya.vsuweather.data.dto

data class Weather(
    val temperature: Int,
    val description: String,
    val weatherState: WeatherState
)
