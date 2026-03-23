package ru.mishbanya.vsuweather.ui.fragments.city

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import kotlinx.serialization.json.Json
import ru.mishbanya.vsuweather.MainActivity
import ru.mishbanya.vsuweather.data.dto.CityWeather
import ru.mishbanya.vsuweather.domain.vm.CityScreenViewModel
import ru.mishbanya.vsuweather.ui.nav.ScreenConfig
import ru.mishbanya.vsuweather.ui.theme.VSUWeatherTheme

class CityScreenFragment : Fragment() {
    private val cityScreenViewModel: CityScreenViewModel by viewModels()

    companion object {
        const val CITY_WEATHER_KEY = "city_weather"

        fun newInstance(cityWeatherID: Long): CityScreenFragment {
            val fragment = CityScreenFragment()
            val args = Bundle()
            args.putString(CITY_WEATHER_KEY, Json.encodeToString(cityWeatherID))
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cityWeatherJson = arguments?.getString(CITY_WEATHER_KEY)
        val cityID = cityWeatherJson?.let { Json.decodeFromString<Long>(it) }
        cityID?.let { cityScreenViewModel.setCity(it) }

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                VSUWeatherTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        CityScreen(
                            modifier = Modifier.padding(innerPadding),
                            cityScreenViewModel = cityScreenViewModel,
                            navigateUp = {
                                (activity as? MainActivity)?.navigateTo(ScreenConfig.MainScreenConfig)
                            }
                        )
                    }
                }
            }
        }
    }
}
