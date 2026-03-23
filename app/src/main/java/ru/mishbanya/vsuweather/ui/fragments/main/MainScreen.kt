package ru.mishbanya.vsuweather.ui.fragments.main

import androidx.compose.foundation.layout.Box
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.data.dto.ext.toIconResource
import ru.mishbanya.vsuweather.data.dto.ext.toTemperatureFormat
import ru.mishbanya.vsuweather.domain.vm.MainScreenViewModel
import ru.mishbanya.vsuweather.ui.nav.ScreenConfig
import java.time.Instant
import java.time.LocalDate
import java.util.Date

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    modifier: Modifier = Modifier,
    navigateTo: (ScreenConfig) -> Unit = {}
) {
    val scope = rememberCoroutineScope()

    val cities by mainScreenViewModel.cities.collectAsState()
    val removeCitySnackBar = remember { SnackbarHostState() }
    var addCityDialog by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = removeCitySnackBar) }
    ) { paddingValues ->
        Box(
            modifier = modifier.fillMaxSize().padding(top = 48.dp, bottom = paddingValues.calculateBottomPadding()),
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize()
            ) {
                itemsIndexed(cities.sortedBy { !it.starred }) { index, cityWeather ->
                    val weatherToday = cityWeather.weather.getValue(LocalDate.now())
                    Card(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                        shape = RoundedCornerShape(16.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
                        onClick = {
                            navigateTo(ScreenConfig.CityScreenConfig(cityWeather))
                        }
                    ){
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            verticalAlignment = Alignment.CenterVertically
                        ){
                            Icon(
                                painterResource(weatherToday.weatherState.toIconResource()),
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
                                    onClick = {
                                        mainScreenViewModel.startCity(cityWeather)
                                    }
                                ) {
                                    Icon(
                                        if(cityWeather.starred) painterResource(R.drawable.ic_star_fill) else painterResource(R.drawable.ic_star),
                                        tint = Color.Yellow,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                                Spacer(modifier = Modifier.height(12.dp))
                                val removeCityAsk = stringResource(R.string.remove_city)
                                val confirmRemoveCity = stringResource(R.string.confirm_btn)
                                IconButton(
                                    onClick = {
                                        scope.launch {
                                            val snackBarResult = removeCitySnackBar.showSnackbar(
                                                message = removeCityAsk,
                                                actionLabel = confirmRemoveCity,
                                                duration = SnackbarDuration.Long
                                            )
                                            if(snackBarResult == SnackbarResult.ActionPerformed){
                                                mainScreenViewModel.removeCity(cityWeather)
                                            }
                                        }
                                    }
                                ) {
                                    Icon(
                                        painterResource(R.drawable.ic_trash),
                                        tint = Color.Red,
                                        contentDescription = null,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        }
                    }
                }
            }
            IconButton(
                onClick = {
                    addCityDialog = true
                },
                modifier = Modifier.align(Alignment.BottomEnd).padding(16.dp)
            ) {
                Icon(
                    painterResource(R.drawable.ic_plus),
                    contentDescription = null,
                    modifier = Modifier.size(128.dp)
                )
            }
        }
    }

    if(addCityDialog){
        AddCityDialog(
            onExit = { addCityDialog = false },
            mainScreenViewModel = mainScreenViewModel
        )
    }
}