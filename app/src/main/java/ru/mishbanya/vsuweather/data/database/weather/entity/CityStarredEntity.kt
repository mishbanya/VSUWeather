package ru.mishbanya.vsuweather.data.database.weather.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city_starred")
data class CityStarredEntity(
    @PrimaryKey
    val cityId: String,
    val isStarred: Boolean
)

