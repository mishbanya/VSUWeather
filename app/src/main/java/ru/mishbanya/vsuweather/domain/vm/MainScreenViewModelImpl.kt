package ru.mishbanya.vsuweather.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.data.dto.CityWeather
import ru.mishbanya.vsuweather.domain.repository.WeatherGetterImpl

class MainScreenViewModelImpl: MainScreenViewModel, ViewModel() {

    private val _cities = MutableStateFlow(listOf<CityWeather>())
    override val cities: StateFlow<List<CityWeather>>
        get() = _cities.asStateFlow()

    override fun addCity(name: String) {
        if(cities.value.any { it.name == name }) return
        val weatherGetter = WeatherGetterImpl()
        viewModelScope.launch {
            _cities.emit(
                cities.value.toMutableList() + CityWeather(name, weatherGetter.getWeather(name))
            )
        }
    }

    override fun removeCity(cityWeather: CityWeather) {
        viewModelScope.launch {
            _cities.emit(cities.value.filter { it != cityWeather })
        }
    }

}