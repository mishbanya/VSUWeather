package ru.mishbanya.vsuweather.presentation.view.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.presentation.vm.dto.ext.toIconResource
import ru.mishbanya.vsuweather.presentation.vm.dto.ext.toTemperatureFormat
import ru.mishbanya.vsuweather.presentation.vm.MainScreenViewModel
import ru.mishbanya.vsuweather.presentation.view.nav.ScreenConfig
import ru.mishbanya.vsuweather.presentation.vm.dto.CityForecastModel
import java.time.LocalDate

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    modifier: Modifier = Modifier,
    navigateTo: (ScreenConfig) -> Unit = {}
) {
    val cities by mainScreenViewModel.cities.collectAsState()

    val isError by mainScreenViewModel.isError.collectAsState()
    val isLoading by mainScreenViewModel.isLoading.collectAsState()

    Column(
        modifier = modifier.fillMaxSize().padding(vertical = 48.dp)
    ) {
        when {
            isLoading -> {
                Spacer(modifier = Modifier.weight(1f))
                CircularProgressIndicator(
                    modifier = Modifier
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            isError -> {
                Spacer(modifier = Modifier.weight(1f))
                Text(
                    stringResource(R.string.is_error),
                    color = Color.Red
                )
                Spacer(modifier = Modifier.weight(1f))
            }
            else -> {
                LazyColumn {
                    itemsIndexed(cities.sortedBy { !it.starred }) { _, cityWeather ->
                        cityCard(
                            cityWeather,
                            onClick = {
                                navigateTo(ScreenConfig.CityScreenConfig(cityWeather.id))
                            },
                            onStar = {
                                mainScreenViewModel.startCity(cityWeather)
                            }
                        )
                    }
                    item {
                    }
                }
                Spacer(modifier = Modifier.weight(1f))
            }
        }
        Button(
            onClick = {
                mainScreenViewModel.updateWeather()
            },
            shape = RoundedCornerShape(10.dp),
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .align(Alignment.CenterHorizontally),
            enabled = !isLoading
        ) {
            Text(stringResource(R.string.update_btn))
        }
    }
}



@Composable
fun cityCard(
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
    ){
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(
                painterResource(weatherToday.icon.toIconResource()),
                contentDescription = null,
                modifier = Modifier.size(48.dp)
            )
            Spacer(modifier = Modifier.width(16.dp))
            Column() {
                Text(
                    text = cityWeather.name,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
                Text(text = weatherToday.description,)
            }
            Spacer(modifier = Modifier.weight(1f))
            Text(
                text = weatherToday.temperature.toTemperatureFormat(),
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )
            Spacer(modifier = Modifier.width(8.dp))
            Column() {
                IconButton(
                    onClick = onStar
                ) {
                    Icon(
                        if(cityWeather.starred) painterResource(R.drawable.ic_star_fill) else painterResource(R.drawable.ic_star),
                        tint = Color.Yellow,
                        contentDescription = null,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.height(12.dp))
            }
        }
    }
}