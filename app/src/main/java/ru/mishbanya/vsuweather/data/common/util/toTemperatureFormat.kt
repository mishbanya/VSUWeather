package ru.mishbanya.vsuweather.data.common.util

import kotlin.math.abs

fun Int.toTemperatureFormat(): String{
    return "${if(this >= 0) "+" else "-"}${abs(this)}°"
}