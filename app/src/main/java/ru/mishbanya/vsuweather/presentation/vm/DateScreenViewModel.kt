package ru.mishbanya.vsuweather.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import org.koin.android.annotation.KoinViewModel
import ru.mishbanya.vsuweather.data.database.weather.LocalWeatherRepository
import ru.mishbanya.vsuweather.data.retrofit.weather.RetrofitWeatherRepository
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.ForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForeCastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toForecastModel

@KoinViewModel
class DateScreenViewModel(
    private val retrofitWeatherRepository: RetrofitWeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository
): ViewModel() {
    private val cityWeatherID = MutableStateFlow("")
    private val dateWeatherID = MutableStateFlow("")

    val forecastModel: StateFlow<ForecastModel?> = combine(
        retrofitWeatherRepository.cities,
        localWeatherRepository.cachedWeather,
        cityWeatherID,
        dateWeatherID
    ) { cities, localCities, cityId, dateId ->
        cities.firstOrNull{
            it.id == cityId
        }?.forecasts?.firstOrNull{
            it.date.toString() == dateId
        }?.toForecastModel()
            ?: localCities.firstOrNull{
                it.id == cityId
            }?.forecasts?.firstOrNull{
                it.date.toString() == dateId
            }?.toForecastModel()
    }.stateIn(
        viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = null
    )

    val isLoadingFromServer = retrofitWeatherRepository.isLoading
    val isServerError = retrofitWeatherRepository.isError

    fun setCity(id: String) {
        cityWeatherID.value = id
        viewModelScope.launch {
            retrofitWeatherRepository.updateWeather()
        }
    }

    fun setDate(id: String) {
        dateWeatherID.value = id
        viewModelScope.launch {
            retrofitWeatherRepository.updateWeather()
        }
    }

    fun updateWeather() {
        viewModelScope.launch {
            retrofitWeatherRepository.updateWeather()
        }
    }

}