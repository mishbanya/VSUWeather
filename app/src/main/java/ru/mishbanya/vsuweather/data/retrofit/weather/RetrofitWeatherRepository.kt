package ru.mishbanya.vsuweather.data.retrofit.weather

import kotlinx.coroutines.flow.StateFlow
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitCityModel

interface RetrofitWeatherRepository {
    val isError: StateFlow<Boolean>
    val isLoading: StateFlow<Boolean>
    val cities: StateFlow<List<RetrofitCityModel>>
    suspend fun updateWeather()
}