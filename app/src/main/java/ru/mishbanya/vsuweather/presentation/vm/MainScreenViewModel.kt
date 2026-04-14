package ru.mishbanya.vsuweather.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import org.koin.android.annotation.KoinViewModel
import ru.mishbanya.vsuweather.data.retrofit.weather.RetrofitWeatherRepository
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForecastModel

@KoinViewModel
class MainScreenViewModel(
    private val retrofitWeatherRepository: RetrofitWeatherRepository
): ViewModel() {

    val cities: StateFlow<List<CityForecastModel>> =
        retrofitWeatherRepository.cities.map { retrofitCityModels ->
            retrofitCityModels.map {
                it.toCityForecastModel()
            }
        }.stateIn(
            viewModelScope,
            started = SharingStarted.Eagerly,
            initialValue = listOf()
        )

    val isLoading = retrofitWeatherRepository.isLoading
    val isError = retrofitWeatherRepository.isError

    fun startCity(cityForecastModel: CityForecastModel) {
        viewModelScope.launch {
            TODO()
        }
    }

    fun updateWeather() {
        viewModelScope.launch {
            retrofitWeatherRepository.updateWeather()
        }
    }
}