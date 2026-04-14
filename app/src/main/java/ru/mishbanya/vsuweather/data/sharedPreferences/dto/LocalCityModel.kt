package ru.mishbanya.vsuweather.data.sharedPreferences.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mishbanya.vsuweather.data.common.LocalDateSerializer
import ru.mishbanya.vsuweather.data.common.dto.WeatherIcon
import java.time.LocalDate

@Serializable
data class LocalCityModel(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("forecasts") val forecasts: List<LocalForecastModel>,
    @SerialName("isStarred") val isStarred: Boolean
)

@Serializable
data class LocalForecastModel(
    @SerialName("id") val id: String,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("date") val date: LocalDate,
    @SerialName("temperature") val temperature: Int,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: WeatherIcon,
    @SerialName("updatedAt") val updatedAt: String
)