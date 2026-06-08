package ru.mishbanya.vsuweather.widget

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.glance.GlanceId
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.action.actionStartActivity
import androidx.glance.action.clickable
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.cornerRadius
import androidx.glance.appwidget.provideContent
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.padding
import androidx.glance.layout.size
import androidx.glance.layout.width
import androidx.glance.text.FontWeight
import androidx.glance.text.Text
import androidx.glance.text.TextStyle
import androidx.glance.unit.ColorProvider
import org.koin.java.KoinJavaComponent.getKoin
import ru.mishbanya.vsuweather.MainActivity
import ru.mishbanya.vsuweather.data.common.dto.toIconResource
import ru.mishbanya.vsuweather.data.database.weather.LocalWeatherRepository
import ru.mishbanya.vsuweather.data.retrofit.weather.RetrofitWeatherRepository
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForeCastModel
import ru.mishbanya.vsuweather.presentation.vm.dto.mappers.toCityForecastModel
import java.time.LocalDate

class WeatherWidget : GlanceAppWidget() {

    private val retrofitWeatherRepository: RetrofitWeatherRepository = getKoin().get()
    private val localWeatherRepository: LocalWeatherRepository = getKoin().get()

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            val retrofitCities by retrofitWeatherRepository.cities.collectAsState(initial = emptyList())
            val cachedWeather by localWeatherRepository.cachedWeather.collectAsState(initial = emptyList())
            val starredCities by localWeatherRepository.starredCities.collectAsState(initial = emptyList())

            val starredMap = starredCities.associateBy({ it.cityId }, { it.isStarred })
            val city = retrofitCities.takeIf { it.isNotEmpty() }?.firstOrNull()?.let {
                it.toCityForecastModel(starredMap[it.id] ?: false)
            } ?: cachedWeather.firstOrNull()?.let {
                it.toCityForeCastModel(starredMap[it.id] ?: false)
            }

            WidgetContent(city = city)
        }
    }
}

@Composable
private fun WidgetContent(city: CityForecastModel?) {
    Box(
        modifier = GlanceModifier
            .fillMaxSize()
            .cornerRadius(20.dp)
            .background(Color(0xFF1565C0))
            .padding(horizontal = 16.dp, vertical = 12.dp)
            .clickable(actionStartActivity<MainActivity>())
    ) {
        city?.let { city ->
            val todaysForecast = city.forecastModel.find { it.date == LocalDate.now() }
                ?: city.forecastModel.firstOrNull()!!

            Row(
                modifier = GlanceModifier.fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    provider = ImageProvider(todaysForecast.icon.toIconResource()),
                    contentDescription = todaysForecast.description,
                    modifier = GlanceModifier.size(56.dp)
                )
                Spacer(modifier = GlanceModifier.width(12.dp))
                Column(verticalAlignment = Alignment.CenterVertically) {
                    Text(
                        text = city.name,
                        style = TextStyle(
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Medium
                        )
                    )
                    Text(
                        text = "${todaysForecast.temperature}°C",
                        style = TextStyle(
                            fontSize = 36.sp,
                            fontWeight = FontWeight.Bold
                        )
                    )
                    Text(
                        text = todaysForecast.description,
                        style = TextStyle(fontSize = 11.sp)
                    )
                }
            }
        } ?: Text(
            text = "Нет данных",
            style = TextStyle(
                color = ColorProvider(Color.White),
                fontSize = 14.sp
            )
        )
    }
}
