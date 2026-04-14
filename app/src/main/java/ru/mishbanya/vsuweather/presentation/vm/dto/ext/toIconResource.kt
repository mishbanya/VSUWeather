package ru.mishbanya.vsuweather.presentation.vm.dto.ext

import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.data.common.dto.WeatherIcon

fun WeatherIcon.toIconResource() = when(this) {
    WeatherIcon.CLEAR -> R.drawable.ic_clear
    WeatherIcon.CLOUDY -> R.drawable.ic_cloudy
    WeatherIcon.RAIN -> R.drawable.ic_rain
    WeatherIcon.OVERCAST -> R.drawable.ic_overcast
    WeatherIcon.PARTLYCLOUDY -> R.drawable.ic_partly_cloudy
    WeatherIcon.STORM -> R.drawable.ic_storm
    WeatherIcon.SUNNY -> R.drawable.ic_sunny
}