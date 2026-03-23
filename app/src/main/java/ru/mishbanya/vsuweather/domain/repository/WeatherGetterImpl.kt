package ru.mishbanya.vsuweather.domain.repository

import ru.mishbanya.vsuweather.data.dto.Weather
import ru.mishbanya.vsuweather.data.dto.WeatherState
import ru.mishbanya.vsuweather.data.repository.WeatherGetter
import kotlin.random.Random

class WeatherGetterImpl: WeatherGetter {
    override fun getWeather(name: String): Weather {
        return Weather(
            temperature = Random.nextInt(-30,30),
            description = "Lorem ipsum",
            weatherState = WeatherState.values().get(Random.nextInt(0,4))
        )
    }
}