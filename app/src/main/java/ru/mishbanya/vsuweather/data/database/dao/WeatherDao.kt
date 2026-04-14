package ru.mishbanya.vsuweather.data.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import ru.mishbanya.vsuweather.data.database.entity.WeatherEntity

@Dao
interface WeatherDao {
//    @Query("SELECT * FROM weather")
//    fun getAllWeather(): Flow<List<WeatherEntity>>
}
