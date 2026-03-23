package ru.mishbanya.vsuweather.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.data.dto.CityWeather
import ru.mishbanya.vsuweather.data.repository.WeatherCommonImpl

class MainScreenViewModel: ViewModel() {

    val cities: StateFlow<List<CityWeather>> = WeatherCommonImpl.cities

    fun addCity(name: String) {
        viewModelScope.launch {
            WeatherCommonImpl.addCity(name)
        }
    }

    fun removeCity(cityWeather: CityWeather) {
        viewModelScope.launch {
            WeatherCommonImpl.removeCity(cityWeather)
        }
    }

    fun startCity(cityWeather: CityWeather) {
        viewModelScope.launch {
            WeatherCommonImpl.startCity(cityWeather)
        }
    }

}