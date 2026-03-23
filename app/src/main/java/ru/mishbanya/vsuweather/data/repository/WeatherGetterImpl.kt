package ru.mishbanya.vsuweather.data.repository

import ru.mishbanya.vsuweather.data.dto.Weather
import ru.mishbanya.vsuweather.data.dto.WeatherState
import ru.mishbanya.vsuweather.domain.repository.WeatherGetter
import java.time.LocalDate
import kotlin.random.Random

object WeatherGetterImpl: WeatherGetter {
    override suspend fun getWeather(name: String, date: LocalDate): Weather {
        return Weather(
            temperature = Random.Default.nextInt(-30, 30),
            description = "Lorem ipsum",
            weatherState = WeatherState.entries[Random.Default.nextInt(0, 4)]
        )
    }
}