package ru.mishbanya.vsuweather.data.retrofit.weather.dto.mappers

import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitCityModel
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitForecastModel
import ru.mishbanya.vsuweather.data.sharedPreferences.dto.LocalCityModel
import ru.mishbanya.vsuweather.data.sharedPreferences.dto.LocalForecastModel

fun RetrofitCityModel.toLocalCityModel(): LocalCityModel {
    return LocalCityModel(
        id = id,
        name = name,
        forecasts = forecasts.map {
            it.toLocalForecastModel()
        },
        isStarred = false
    )
}

fun RetrofitForecastModel.toLocalForecastModel(): LocalForecastModel {
    return LocalForecastModel(
        id = id,
        date = date,
        temperature = temperature,
        description = description,
        icon = icon,
        updatedAt = updatedAt

    )
}