package ru.mishbanya.vsuweather.presentation.view.screens.city

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.presentation.view.common.ScreenWithLoading
import ru.mishbanya.vsuweather.presentation.view.common.UpdateButtonWithIndicators
import ru.mishbanya.vsuweather.presentation.view.screens.city.util.CityScreenSort
import ru.mishbanya.vsuweather.presentation.view.screens.city.util.toStringRes
import ru.mishbanya.vsuweather.presentation.vm.CityScreenViewModel

@Composable
fun CityScreen(
    modifier: Modifier = Modifier,
    cityScreenViewModel: CityScreenViewModel,
    navigateUp: () -> Unit = {}
) {
    val cityWeather by cityScreenViewModel.cityForecastModel.collectAsState()

    val isError by cityScreenViewModel.isServerError.collectAsState()
    val isLoading by cityScreenViewModel.isLoadingFromServer.collectAsState()

    var sortedBy by remember { mutableStateOf<CityScreenSort>(CityScreenSort.DATE) }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 48.dp),
    ) {
        ScreenWithLoading(
            isLoading = isLoading,
            content = cityWeather?.let{
                {
                    Text(
                        text = it.name,
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp, vertical = 8.dp),
                    ) {
                        CityScreenSort.entries.forEach { sortType ->
                            Button(
                                onClick = {
                                    sortedBy = sortType
                                },
                                shape = RoundedCornerShape(10.dp),
                                modifier = Modifier
                                    .padding(horizontal = 16.dp)
                                    .weight(1f),
                                enabled = sortedBy != sortType
                            ) {
                                Text(stringResource(sortType.toStringRes()))
                            }
                        }
                    }
                    LazyColumn {
                        itemsIndexed(
                            items = it.forecastModel
                                .map {
                                    Pair(it.key, it.value)
                                }
                                .let { list ->
                                    when(sortedBy){
                                        CityScreenSort.DATE -> list.sortedBy { pair -> pair.first }
                                        CityScreenSort.TEMPERATURE -> list.sortedBy { pair -> pair.second.temperature }
                                    }
                                }
                        ) { _, dateWeather ->
                            CityScreenDateCard(
                                dateWeather = dateWeather
                            )
                        }
                    }
                    Spacer(modifier = Modifier.weight(1f))
                }
            }
        )
        UpdateButtonWithIndicators(
            isError,
            isLoading
        ) { cityScreenViewModel.updateWeather() }
    }
}