package ru.mishbanya.vsuweather.domain.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.data.dto.CityWeather
import ru.mishbanya.vsuweather.data.repository.WeatherCommonImpl

class CityScreenViewModel: ViewModel() {
    
    private var cityWeatherID: Long = 0

    private val _cityWeather = MutableStateFlow<CityWeather?>(null)
    val cityWeather: StateFlow<CityWeather?>
        get() = _cityWeather.asStateFlow()

    init {
        WeatherCommonImpl.cities.onEach {
            _cityWeather.emit(
                it.firstOrNull
                {
                    it.id == cityWeatherID
                }
            )
        }.launchIn(viewModelScope)
    }

    fun setCity(id: Long) {
        cityWeatherID = id
        viewModelScope.launch {
            _cityWeather.emit(
                WeatherCommonImpl.cities.value.firstOrNull
                {
                    it.id == cityWeatherID
                }
            )
        }
    }
}
