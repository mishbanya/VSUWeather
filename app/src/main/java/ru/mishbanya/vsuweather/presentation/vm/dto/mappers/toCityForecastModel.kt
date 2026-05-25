package ru.mishbanya.vsuweather.presentation.vm.dto.mappers

import ru.mishbanya.vsuweather.data.database.weather.entity.WeatherEntity
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitCityModel
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.ForecastModel

fun RetrofitCityModel.toCityForecastModel(isStarred: Boolean): CityForecastModel {
    return CityForecastModel(
        id = id,
        name = name,
        forecastModel = forecasts.map {
            it.toForecastModel()
        },
        starred = isStarred
    )
}

fun WeatherEntity.toCityForeCastModel(isStarred: Boolean): CityForecastModel {
    return CityForecastModel(
        id = id,
        name = name,
        forecastModel = forecasts.map {
            it.toForecastModel()
        },
        starred = isStarred
    )
}

fun RetrofitForecastModel.toForecastModel(): ForecastModel {
    return ForecastModel(
        date = date,
        temperature = temperature,
        description = description,
        icon = icon
    )
}