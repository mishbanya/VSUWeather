package ru.mishbanya.vsuweather.presentation.view.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.data.common.dto.toIconResource
import ru.mishbanya.vsuweather.data.common.util.toTemperatureFormat
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import java.time.LocalDate

@Composable
internal fun MainScreenСityCard(
    cityWeather: CityForecastModel,
    onClick: () -> Unit,
    onStar: () -> Unit
){
    val weatherToday = cityWeather.forecastModel[LocalDate.now()] ?: cityWeather.forecastModel.values.firstOrNull() ?: return

    Card(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier.Companion
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.Companion.CenterVertically
        ) {
            Icon(
                painterResource(weatherToday.icon.toIconResource()),
                contentDescription = null,
                modifier = Modifier.Companion.size(48.dp)
            )
            Spacer(modifier = Modifier.Companion.width(16.dp))
            Column() {
                Text(
                    text = cityWeather.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold
                )
                Text(text = weatherToday.description,)
            }
            Spacer(modifier = Modifier.Companion.weight(1f))
            Text(
                text = weatherToday.temperature.toTemperatureFormat(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Companion.Bold
            )
            Spacer(modifier = Modifier.Companion.width(8.dp))
            Column() {
                IconButton(
                    onClick = onStar
                ) {
                    Icon(
                        if (cityWeather.starred) painterResource(R.drawable.ic_star_fill) else painterResource(
                            R.drawable.ic_star
                        ),
                        tint = Color.Companion.Yellow,
                        contentDescription = null,
                        modifier = Modifier.Companion.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.Companion.height(12.dp))
            }
        }
    }
}