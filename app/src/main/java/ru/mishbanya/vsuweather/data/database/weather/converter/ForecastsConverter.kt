package ru.mishbanya.vsuweather.data.database.weather.converter

import androidx.room.TypeConverter
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitForecastModel

class ForecastsConverter {
    @TypeConverter
    fun fromForecastsList(value: List<RetrofitForecastModel>): String {
        return Json.encodeToString(value)
    }

    @TypeConverter
    fun toForecastsList(value: String): List<RetrofitForecastModel> {
        return Json.decodeFromString(value)
    }
}

