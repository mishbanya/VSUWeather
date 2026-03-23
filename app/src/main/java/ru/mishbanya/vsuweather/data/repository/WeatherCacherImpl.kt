package ru.mishbanya.vsuweather.data.repository

import ru.mishbanya.vsuweather.data.common.WeatherSharedPreferences
import ru.mishbanya.vsuweather.data.dto.CityWeather
import ru.mishbanya.vsuweather.domain.repository.WeatherCacher
import androidx.core.content.edit
import kotlinx.serialization.json.Json

object WeatherCacherImpl: WeatherCacher {

    const val KEY = "weather_state"

    val preferences = WeatherSharedPreferences.preferences

    override suspend fun getWeatherState(): List<CityWeather> {
        return preferences.getString(KEY, "[]")?.let {
            Json.decodeFromString<List<CityWeather>>(it)
        } ?: listOf()
    }

    override suspend fun saveWeatherState(list: List<CityWeather>) {
        preferences.edit { putString(KEY, Json.encodeToString(list)) }
    }
}