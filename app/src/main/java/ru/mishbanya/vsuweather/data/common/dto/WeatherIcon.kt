package ru.mishbanya.vsuweather.data.common.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mishbanya.vsuweather.R

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

fun WeatherIcon.toIconResource() = when(this) {
    WeatherIcon.CLEAR -> R.drawable.ic_clear
    WeatherIcon.CLOUDY -> R.drawable.ic_cloudy
    WeatherIcon.RAIN -> R.drawable.ic_rain
    WeatherIcon.OVERCAST -> R.drawable.ic_overcast
    WeatherIcon.PARTLYCLOUDY -> R.drawable.ic_partly_cloudy
    WeatherIcon.STORM -> R.drawable.ic_storm
    WeatherIcon.SUNNY -> R.drawable.ic_sunny
}