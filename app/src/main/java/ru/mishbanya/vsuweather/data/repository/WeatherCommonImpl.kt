package ru.mishbanya.vsuweather.data.repository

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.data.dto.CityWeather
import ru.mishbanya.vsuweather.domain.repository.WeatherCommon
import java.time.LocalDate

object WeatherCommonImpl: WeatherCommon {
    private val scope = CoroutineScope(Dispatchers.IO + SupervisorJob())

    private val _cities = MutableStateFlow(listOf<CityWeather>())
    override val cities: StateFlow<List<CityWeather>>
        get() = _cities.asStateFlow()

    init {
        scope.launch {
            _cities.emit(WeatherCacherImpl.getWeatherState())
        }

        cities.onEach {
            WeatherCacherImpl.saveWeatherState(it)
        }.launchIn(scope)
    }

    override suspend fun addCity(name: String) {
        if(cities.value.any { it.name == name }) return
        _cities.emit(
            (cities.value.toMutableList() + CityWeather(
                name = name,
                weather = buildMap {
                    LocalDate.now().let {
                        put(it, WeatherGetterImpl.getWeather(
                            name,
                            it
                        ))
                    }
                    LocalDate.now().plusDays(1).let {
                        put(it, WeatherGetterImpl.getWeather(
                            name,
                            it
                        ))
                    }
                    LocalDate.now().plusDays(2).let {
                        put(it, WeatherGetterImpl.getWeather(
                            name,
                            it
                        ))
                    }
                }
            ))
        )
    }

    override suspend fun removeCity(cityWeather: CityWeather) {
        _cities.emit(cities.value.filter { it != cityWeather })
    }

    override suspend fun startCity(cityWeather: CityWeather) {
        _cities.emit(
            cities.value.map {
                if(it == cityWeather) it.copy(starred = !it.starred) else it
            }.toMutableList().apply {})
    }

    override suspend fun updateWeather() {
        TODO("Not yet implemented")
    }


}