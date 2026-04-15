package ru.mishbanya.vsuweather.presentation.view.screens.main

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import ru.mishbanya.vsuweather.R
import ru.mishbanya.vsuweather.presentation.view.common.LoadingWIthErrorsScreen
import ru.mishbanya.vsuweather.presentation.vm.MainScreenViewModel
import ru.mishbanya.vsuweather.presentation.nav.ScreenConfig

@Composable
fun MainScreen(
    mainScreenViewModel: MainScreenViewModel,
    modifier: Modifier = Modifier,
    navigateTo: (ScreenConfig) -> Unit = {}
) {
    val cities by mainScreenViewModel.cities.collectAsState()

    val isError by mainScreenViewModel.isError.collectAsState()
    val isLoading by mainScreenViewModel.isLoading.collectAsState()

    var searchQuery by remember { mutableStateOf("") }

    Column(
        modifier = modifier.fillMaxSize().padding(vertical = 48.dp)
    ) {
        LoadingWIthErrorsScreen(
            isError = isError,
            isLoading = isLoading,
            content = {
                TextField(
                    value = searchQuery,
                    onValueChange = {
                        searchQuery = it
                    },
                    modifier = Modifier.fillMaxWidth().padding(horizontal = 16.dp, vertical = 8.dp)
                )
                LazyColumn {
                    itemsIndexed(
                        items = cities
                            .filter { it.name.contains(searchQuery, true) }
                            .sortedBy { !it.starred }
                    ) { _, cityWeather ->
                        MainScreenСityCard(
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
        )
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