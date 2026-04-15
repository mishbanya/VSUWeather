package ru.mishbanya.vsuweather.presentation.vm

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
class MainScreenViewModel(
    private val retrofitWeatherRepository: RetrofitWeatherRepository,
    private val localWeatherRepository: LocalWeatherRepository
): ViewModel() {

    val cities: StateFlow<List<CityForecastModel>> = combine(
        retrofitWeatherRepository.cities,
        localWeatherRepository.cachedWeather,
        localWeatherRepository.starredCities
    ) { retrofitCityModels, cachedCityModels, starredCitiesList ->
        val starredMap = starredCitiesList.associateBy({ it.cityId }, { it.isStarred })
        retrofitCityModels.takeIf { it.isNotEmpty() }?.map {
            it.toCityForecastModel(starredMap[it.id] ?: false)
        } ?: cachedCityModels.map {
            it.toCityForeCastModel(starredMap[it.id] ?: false)
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
            localWeatherRepository.setCityStarred(cityForecastModel.id, !cityForecastModel.starred)
        }
    }

    fun updateWeather() {
        viewModelScope.launch {
            retrofitWeatherRepository.updateWeather()
        }
    }
}