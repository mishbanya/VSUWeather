package ru.mishbanya.vsuweather.presentation.view.screens.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mishbanya.vsuweather.data.common.dto.toIconResource
import ru.mishbanya.vsuweather.data.common.util.toTemperatureFormat
import ru.mishbanya.vsuweather.presentation.vm.dto.ForecastModel
import java.time.LocalDate

@Composable
fun CityScreenDateCard(
    weather: ForecastModel,
    onClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp, vertical = 8.dp),
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = onClick
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painterResource(weather.icon.toIconResource()),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(
                    text = weather.date.toString(),
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Companion.Bold
                )
                Text(text = weather.description,)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = weather.temperature.toTemperatureFormat(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Companion.Bold
            )
        }
    }
}