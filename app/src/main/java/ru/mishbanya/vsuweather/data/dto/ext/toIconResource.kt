package ru.mishbanya.vsuweather.data.dto.ext

import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.data.dto.WeatherState

fun WeatherState.toIconResource() = when(this) {
    WeatherState.CLEAR -> R.drawable.ic_clear
    WeatherState.CLOUDY -> R.drawable.ic_cloudy
    WeatherState.RAIN -> R.drawable.ic_rain
    WeatherState.SNOW -> R.drawable.ic_snow
    WeatherState.THUNDERSTORM -> R.drawable.ic_thunderstorm
}