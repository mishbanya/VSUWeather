package ru.mishbanya.vsuweather.presentation.vm.dto

import kotlinx.serialization.Serializable
import ru.mishbanya.vsuweather.data.common.LocalDateSerializer
import ru.mishbanya.vsuweather.data.common.dto.WeatherIcon
import java.time.LocalDate
import java.util.UUID

@Serializable
data class CityForecastModel(
    val id: String = UUID.randomUUID().toString(),
    val name: String,
    val forecastModel: List<ForecastModel>,
    val starred: Boolean = false
)

@Serializable
data class ForecastModel(
    @Serializable(with = LocalDateSerializer::class) val date: LocalDate,
    val temperature: Int,
    val description: String,
    val icon: WeatherIcon
)