package ru.mishbanya.vsuweather.presentation.view.screens.date

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.mishbanya.vsuweather.data.common.dto.toIconResource
import ru.mishbanya.vsuweather.presentation.nav.ScreenConfig
import ru.mishbanya.vsuweather.presentation.view.common.ScreenWithLoading
import ru.mishbanya.vsuweather.presentation.view.common.UpdateButtonWithIndicators
import ru.mishbanya.vsuweather.presentation.view.screens.city.CityScreenDateCard
import ru.mishbanya.vsuweather.presentation.view.screens.city.util.CityScreenSort
import ru.mishbanya.vsuweather.presentation.view.screens.city.util.toStringRes
import ru.mishbanya.vsuweather.presentation.vm.DateScreenViewModel

@Composable
fun DateScreen(
    modifier: Modifier = Modifier,
    dateScreenViewModel: DateScreenViewModel,
    navigateUp: () -> Unit = {}
) {
    val weather by dateScreenViewModel.forecastModel.collectAsState()

    val isError by dateScreenViewModel.isServerError.collectAsState()
    val isLoading by dateScreenViewModel.isLoadingFromServer.collectAsState()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(vertical = 48.dp),
    ) {
        ScreenWithLoading(
            isLoading = isLoading,
            content = weather?.let{ weather ->
                {
                    Text(
                        text = weather.date.toString(),
                        fontSize = 24.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        modifier = Modifier.fillMaxWidth()
                    )
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp)
                    ) {
                        Column{
                            Text(
                                text = weather.temperature.toString(),
                                fontSize = 22.sp,
                                fontWeight = FontWeight.Bold
                            )
                            Spacer(modifier = Modifier.height(10.dp))
                            Text(
                                text = weather.description.toString(),
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.weight(1f))
                        Icon(
                            painter = painterResource(weather.icon.toIconResource()),
                            contentDescription = null,
                            modifier = Modifier.size(64.dp)
                        )
                    }
                    Spacer(
                        modifier = Modifier.weight(1f)
                    )
                }
            }
        )
        UpdateButtonWithIndicators(
            isError,
            isLoading
        ) { dateScreenViewModel.updateWeather() }
    }
}