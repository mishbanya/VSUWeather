package ru.mishbanya.vsuweather.data.database

import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import ru.mishbanya.vsuweather.data.database.weather.dao.CityStarredDao
import ru.mishbanya.vsuweather.data.database.weather.dao.WeatherDao

@Module
class DatabaseModule {
    @Single
    fun provideWeatherDao(appDatabase: AppDatabase): WeatherDao = appDatabase.weatherDao()

    @Single
    fun provideCityStarredDao(appDatabase: AppDatabase): CityStarredDao = appDatabase.cityStarredDao()
}