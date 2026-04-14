package ru.mishbanya.vsuweather.presentation.vm.dto.mappers

import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitCityModel
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.ForecastModel

fun RetrofitCityModel.toCityForecastModel(): CityForecastModel {
    return CityForecastModel(
        id = id,
        name = name,
        forecastModel = forecasts.associate {
            it.date to it.toForecastModel()
        },
        starred = false
    )
}

fun RetrofitForecastModel.toForecastModel(): ForecastModel {
    return ForecastModel(
        temperature = temperature,
        description = description,
        icon = icon
    )
}