package ru.mishbanya.vsuweather.data.sharedPreferences

import android.content.SharedPreferences
import androidx.core.content.edit
import kotlinx.serialization.json.Json
import org.koin.core.annotation.Single
import ru.mishbanya.vsuweather.data.sharedPreferences.dto.LocalCityModel
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel

@Single(binds = [WeatherCacher::class])
class WeatherCacherImpl(
    private val preferences: SharedPreferences
): WeatherCacher {

    companion object {
        const val KEY = "weather_state"
    }

    override suspend fun getWeatherState(): List<LocalCityModel> {
        return preferences.getString(KEY, "[]")?.let {
            Json.decodeFromString<List<LocalCityModel>>(it)
        } ?: listOf()
    }

    override suspend fun saveWeatherState(list: List<LocalCityModel>) {
        preferences.edit { putString(KEY, Json.encodeToString(list)) }
    }
}