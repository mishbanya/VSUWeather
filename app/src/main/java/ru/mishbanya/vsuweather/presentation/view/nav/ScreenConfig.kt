package ru.mishbanya.vsuweather.presentation.view.nav

import kotlinx.serialization.Serializable
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel

@Serializable
sealed class ScreenConfig {
    @Serializable
    object MainScreenConfig: ScreenConfig()
    @Serializable
    class CityScreenConfig(val cityID: String): ScreenConfig()

    companion object{
        const val KEY = "screen_config"
    }
}