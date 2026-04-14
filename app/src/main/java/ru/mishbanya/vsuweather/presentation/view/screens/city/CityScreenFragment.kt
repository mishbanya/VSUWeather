package ru.mishbanya.vsuweather.presentation.view.screens.city

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
import org.koin.androidx.viewmodel.ext.android.viewModel
import ru.mishbanya.vsuweather.MainActivity
import ru.mishbanya.vsuweather.presentation.vm.CityScreenViewModel
import ru.mishbanya.vsuweather.presentation.view.nav.ScreenConfig
import ru.mishbanya.vsuweather.presentation.view.theme.VSUWeatherTheme

class CityScreenFragment : Fragment() {
    private val cityScreenViewModel: CityScreenViewModel by viewModel()

    companion object {
        const val CITY_WEATHER_KEY = "city_weather"

        fun newInstance(cityId: String): CityScreenFragment {
            val fragment = CityScreenFragment()
            val args = Bundle()
            args.putString(CITY_WEATHER_KEY, cityId)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        arguments?.getString(CITY_WEATHER_KEY)?.let { cityScreenViewModel.setCity(it) }

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
