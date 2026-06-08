package ru.mishbanya.vsuweather.data.retrofit.weather

import android.content.Context
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import ru.mishbanya.vsuweather.data.database.weather.LocalWeatherRepository
import ru.mishbanya.vsuweather.data.retrofit.weather.api.WeatherApi
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.RetrofitCityModel
import ru.mishbanya.vsuweather.data.retrofit.weather.dto.mappers.toWeatherEntity
import ru.mishbanya.vsuweather.notifications.WeatherEventBus

@Single(binds = [RetrofitWeatherRepository::class])
class RetrofitWeatherRepositoryImpl(
    private val retrofit: Retrofit,
    private val localWeatherRepository: LocalWeatherRepository,
    private val context: Context
): RetrofitWeatherRepository {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _isError = MutableStateFlow(false)
    override val isError: StateFlow<Boolean>
        get() = _isError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    override val isLoading: StateFlow<Boolean>
        get() = _isLoading.asStateFlow()

    private val _cities = MutableStateFlow(listOf<RetrofitCityModel>())
    override val cities: StateFlow<List<RetrofitCityModel>>
        get() = _cities.asStateFlow()

    private val api: WeatherApi by lazy { retrofit.create(WeatherApi::class.java) }

    init {
        scope.launch {
            while (isActive) {
                updateWeather()
                delay(60_000)
            }
        }
    }

    override suspend fun updateWeather() {
        _isLoading.emit(true)
        try {
            val response = api.getWeather()
            localWeatherRepository.saveWeather(response.cities.map { it.toWeatherEntity() })
            _cities.emit(response.cities)
            _isError.emit(false)
            WeatherEventBus.emitWeatherUpdated()
        } catch (e: Exception) {
            _cities.emit(listOf())
            _isError.emit(true)
            e.printStackTrace()
        } finally {
            _isLoading.emit(false)
        }
    }
}