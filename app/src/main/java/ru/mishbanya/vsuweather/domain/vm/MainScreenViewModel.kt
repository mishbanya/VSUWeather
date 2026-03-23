package ru.mishbanya.vsuweather.domain.vm

import kotlinx.coroutines.flow.StateFlow
import ru.mishbanya.vsuweather.data.dto.CityWeather

interface MainScreenViewModel {
    val cities: StateFlow<List<CityWeather>>

    fun addCity(name: String)
    fun removeCity(cityWeather: CityWeather)
}