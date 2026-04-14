package ru.mishbanya.vsuweather.data.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
enum class WeatherIcon{
    @SerialName("cloudy") CLOUDY,
    @SerialName("rain") RAIN,
    @SerialName("overcast") OVERCAST,
    @SerialName("partly_cloudy") PARTLYCLOUDY,
    @SerialName("storm") STORM,
    @SerialName("sunny") SUNNY,
    @SerialName("clear") CLEAR,
}