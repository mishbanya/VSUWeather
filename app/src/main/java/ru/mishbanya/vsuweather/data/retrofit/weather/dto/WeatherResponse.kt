package ru.mishbanya.vsuweather.data.retrofit.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherResponse(
    @SerialName("cities") val cities: List<RetrofitCityModel>
)