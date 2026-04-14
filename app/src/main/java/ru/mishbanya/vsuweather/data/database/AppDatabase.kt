package ru.mishbanya.vsuweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.mishbanya.vsuweather.data.database.dao.WeatherDao
import ru.mishbanya.vsuweather.data.database.entity.WeatherEntity

@Database(
    entities = [WeatherEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
}
