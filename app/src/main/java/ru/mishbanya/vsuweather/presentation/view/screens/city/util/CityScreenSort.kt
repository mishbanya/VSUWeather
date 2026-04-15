package ru.mishbanya.vsuweather.presentation.view.screens.city.util

import ru.mishbanya.vsuweather.R

enum class CityScreenSort {
    DATE,
    TEMPERATURE,
}

fun CityScreenSort.toStringRes() = when(this) {
    CityScreenSort.DATE -> R.string.sort_by_date
    CityScreenSort.TEMPERATURE -> R.string.sort_by_temp
}