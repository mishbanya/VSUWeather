package ru.mishbanya.vsuweather

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ru.mishbanya.vsuweather.domain.vm.MainScreenViewModelImpl
import ru.mishbanya.vsuweather.ui.screens.main.MainScreen
import ru.mishbanya.vsuweather.ui.theme.VSUWeatherTheme

class MainActivity : ComponentActivity() {

    val mainScreenViewModel = MainScreenViewModelImpl()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            VSUWeatherTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    MainScreen(
                        mainScreenViewModel = mainScreenViewModel
                    )
                }
            }
        }
    }
}