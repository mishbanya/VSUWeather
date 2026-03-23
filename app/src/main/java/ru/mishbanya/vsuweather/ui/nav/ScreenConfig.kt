package ru.mishbanya.vsuweather.ui.nav

import kotlinx.serialization.Serializable
import ru.mishbanya.vsuweather.data.dto.CityWeather

@Serializable
sealed class ScreenConfig {
    @Serializable
    object MainScreenConfig: ScreenConfig()
    @Serializable
    class CityScreenConfig(val cityWeather: CityWeather): ScreenConfig()

    companion object{
        const val KEY = "screen_config"
    }
}