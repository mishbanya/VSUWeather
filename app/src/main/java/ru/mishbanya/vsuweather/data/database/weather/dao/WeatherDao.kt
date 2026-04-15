package ru.mishbanya.vsuweather.data.database.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mishbanya.vsuweather.data.database.weather.entity.WeatherEntity

@Dao
interface WeatherDao {
    @Query("SELECT * FROM weather_cache")
    fun getAllWeather(): Flow<List<WeatherEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeather(weather: WeatherEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWeatherList(weatherList: List<WeatherEntity>)

    @Query("DELETE FROM weather_cache")
    suspend fun clearWeather()
}
