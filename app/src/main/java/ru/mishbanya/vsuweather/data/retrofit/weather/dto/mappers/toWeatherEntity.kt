package ru.mishbanya.vsuweather.data.retrofit.weather.dto.mappers

import ru.mishbanya.vsuweather.data.database.weather.entity.WeatherEntity
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitCityModel

fun RetrofitCityModel.toWeatherEntity(): WeatherEntity {
    return WeatherEntity(id, name, forecasts)
}