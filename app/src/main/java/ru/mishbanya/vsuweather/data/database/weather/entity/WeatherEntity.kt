package ru.mishbanya.vsuweather.data.database.weather.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitForecastModel

@Entity(tableName = "weather_cache")
data class WeatherEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val forecasts: List<RetrofitForecastModel>
)
