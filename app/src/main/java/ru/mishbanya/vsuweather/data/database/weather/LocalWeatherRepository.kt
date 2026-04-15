package ru.mishbanya.vsuweather.data.database.weather

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import ru.mishbanya.vsuweather.data.database.weather.entity.CityStarredEntity
import ru.mishbanya.vsuweather.data.database.weather.entity.WeatherEntity

interface LocalWeatherRepository {
    val cachedWeather: StateFlow<List<WeatherEntity>>
    val starredCities: StateFlow<List<CityStarredEntity>>

    suspend fun saveWeather(weatherList: List<WeatherEntity>)
    suspend fun clearWeather()

    suspend fun setCityStarred(cityId: String, isStarred: Boolean)
    suspend fun isCityStarred(cityId: String): Boolean?
}

