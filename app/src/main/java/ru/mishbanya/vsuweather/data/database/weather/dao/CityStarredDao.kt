package ru.mishbanya.vsuweather.data.database.weather.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import ru.mishbanya.vsuweather.data.database.weather.entity.CityStarredEntity

@Dao
interface CityStarredDao {
    @Query("SELECT * FROM city_starred")
    fun getAllStarred(): Flow<List<CityStarredEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStarred(cityStarred: CityStarredEntity)

    @Query("SELECT isStarred FROM city_starred WHERE cityId = :cityId")
    suspend fun isCityStarred(cityId: String): Boolean?
}

