package ru.mishbanya.vsuweather.data.database.weather

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import org.koin.core.annotation.Single
import ru.mishbanya.vsuweather.data.database.weather.dao.CityStarredDao
import ru.mishbanya.vsuweather.data.database.weather.dao.WeatherDao
import ru.mishbanya.vsuweather.data.database.weather.entity.CityStarredEntity
import ru.mishbanya.vsuweather.data.database.weather.entity.WeatherEntity

@Single(binds = [LocalWeatherRepository::class])
class LocalWeatherRepositoryImpl(
    private val weatherDao: WeatherDao,
    private val cityStarredDao: CityStarredDao
) : LocalWeatherRepository {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    override val cachedWeather: StateFlow<List<WeatherEntity>> = weatherDao.getAllWeather().stateIn(
        scope,
        SharingStarted.Eagerly,
        emptyList()
    )

    override val starredCities: StateFlow<List<CityStarredEntity>> = cityStarredDao.getAllStarred().stateIn(
        scope,
        SharingStarted.Eagerly,
        emptyList()
    )

    override suspend fun saveWeather(weatherList: List<WeatherEntity>) {
        weatherDao.insertWeatherList(weatherList)
    }

    override suspend fun clearWeather() {
        weatherDao.clearWeather()
    }

    override suspend fun setCityStarred(cityId: String, isStarred: Boolean) {
        cityStarredDao.insertStarred(CityStarredEntity(cityId = cityId, isStarred = isStarred))
    }

    override suspend fun isCityStarred(cityId: String): Boolean? {
        return cityStarredDao.isCityStarred(cityId)
    }
}
