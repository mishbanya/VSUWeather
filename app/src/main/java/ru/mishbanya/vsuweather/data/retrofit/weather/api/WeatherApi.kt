package ru.mishbanya.vsuweather.data.retrofit.weather.api

import retrofit2.http.GET
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.WeatherResponse

interface WeatherApi {
    @GET("/weather-forecast")
    suspend fun getWeather(): WeatherResponse
}