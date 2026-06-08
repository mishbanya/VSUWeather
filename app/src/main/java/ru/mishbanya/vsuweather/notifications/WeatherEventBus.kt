package ru.mishbanya.vsuweather.notifications

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object WeatherEventBus {
    private val _weatherUpdated = MutableSharedFlow<Unit>(extraBufferCapacity = 1)
    val weatherUpdated: SharedFlow<Unit> = _weatherUpdated.asSharedFlow()

    fun emitWeatherUpdated() {
        _weatherUpdated.tryEmit(Unit)
    }
}