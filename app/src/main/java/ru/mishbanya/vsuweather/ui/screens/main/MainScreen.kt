package ru.mishbanya.vsuweather.ui.screens.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.data.dto.ext.toIconResource
import ru.mishbanya.vsuweather.data.dto.ext.toTemperatureFormat
import ru.mishbanya.vsuweather.domain.vm.MainScreenViewModel

@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
    mainScreenViewModel: MainScreenViewModel
) {
    val cities by mainScreenViewModel.cities.collectAsState()

    var addCityDialog by remember { mutableStateOf(false) }

    Box(
        modifier = modifier.fillMaxSize().padding(vertical = 48.dp),
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            itemsIndexed(cities) { index, cityWeather ->
                Card(
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp),
                    shape = RoundedCornerShape(16.dp),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ){
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ){
                        Icon(
                            painterResource(cityWeather.weather.weatherState.toIconResource()),
                            contentDescription = null
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        Column() {
                            Text(
                                text = cityWeather.name,
                                fontSize = 20.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Text(text = cityWeather.weather.description,)
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = cityWeather.weather.temperature.toTemperatureFormat(),
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold
                        )
                        Spacer(modifier = Modifier.width(16.dp))
                        IconButton(
                            onClick = {
                                mainScreenViewModel.removeCity(cityWeather)
                            }
                        ) {
                            Icon(
                                painterResource(R.drawable.ic_trash),
                                tint = Color.Red,
                                contentDescription = null
                            )
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

    if(addCityDialog){
        AddCityDialog(
            onExit = { addCityDialog = false },
            mainScreenViewModel = mainScreenViewModel
        )
    }
}