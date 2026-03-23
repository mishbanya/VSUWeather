package ru.mishbanya.vsuweather.data.dto.ext

import kotlin.math.abs

fun Int.toTemperatureFormat(): String{
    return "${if(this >= 0) "+" else "-"}${abs(this)}°"
}