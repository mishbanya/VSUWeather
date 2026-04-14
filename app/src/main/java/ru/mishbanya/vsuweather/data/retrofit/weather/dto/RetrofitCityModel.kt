package ru.mishbanya.vsuweather.data.retrofit.weather.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import ru.mishbanya.vsuweather.data.common.LocalDateSerializer
import ru.mishbanya.vsuweather.data.common.dto.WeatherIcon
import java.time.LocalDate

@Serializable
data class RetrofitCityModel(
    @SerialName("id") val id: String,
    @SerialName("name") val name: String,
    @SerialName("forecasts") val forecasts: List<RetrofitForecastModel>
)

@Serializable
data class RetrofitForecastModel(
    @SerialName("id") val id: String,
    @Serializable(with = LocalDateSerializer::class)
    @SerialName("date") val date: LocalDate,
    @SerialName("temperature") val temperature: Int,
    @SerialName("description") val description: String,
    @SerialName("icon") val icon: WeatherIcon,
    @SerialName("updatedAt") val updatedAt: String
)