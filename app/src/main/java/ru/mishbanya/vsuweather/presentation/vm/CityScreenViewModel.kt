package ru.mishbanya.vsuweather.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import org.koin.android.annotation.KoinViewModel
import ru.mishbanya.vsuweather.data.database.weather.LocalWeatherRepository
import ru.mishbanya.vsuweather.data.retrofit.weather.RetrofitWeatherRepository
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForeCastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForecastModel

@KoinViewModel
class CityScreenViewModel(
    private val retrofitWeatherRepository: RetrofitWeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository
): ViewModel() {
    private val cityWeatherID = MutableStateFlow("")

    val cityForecastModel: StateFlow<CityForecastModel?> = combine(
        retrofitWeatherRepository.cities,
        localWeatherRepository.cachedWeather,
        cityWeatherID,
        localWeatherRepository.starredCities
    ) { cities, localCities, id, starredCities ->
        val starredMap = starredCities.associateBy({ it.cityId }, { it.isStarred })
        cities.firstOrNull {
            it.id == id
        }?.toCityForecastModel(starredMap[id] ?: false)
        ?: localCities.firstOrNull{
            it.id == id
        }?.toCityForeCastModel(starredMap[id] ?: false)
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

    fun updateWeather() {
        viewModelScope.launch {
            retrofitWeatherRepository.updateWeather()
        }
    }
}
