package ru.mishbanya.vsuweather.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.mishbanya.vsuweather.data.database.weather.converter.ForecastsConverter
import ru.mishbanya.vsuweather.data.database.weather.dao.CityStarredDao
import ru.mishbanya.vsuweather.data.database.weather.dao.WeatherDao
import ru.mishbanya.vsuweather.data.database.weather.entity.CityStarredEntity
import ru.mishbanya.vsuweather.data.database.weather.entity.WeatherEntity

@Database(
    entities = [WeatherEntity::class, CityStarredEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(ForecastsConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun weatherDao(): WeatherDao
    abstract fun cityStarredDao(): CityStarredDao
}
