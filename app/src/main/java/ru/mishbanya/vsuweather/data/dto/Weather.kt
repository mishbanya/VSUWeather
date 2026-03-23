package ru.mishbanya.vsuweather.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Weather(
    val temperature: Int,
    val description: String,
    val weatherState: WeatherState
)
