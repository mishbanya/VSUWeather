package ru.mishbanya.vsuweather.presentation.view.screens.date

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
import ru.mishbanya.vsuweather.presentation.nav.ScreenConfig
import ru.mishbanya.vsuweather.presentation.view.theme.VSUWeatherTheme
import ru.mishbanya.vsuweather.presentation.vm.DateScreenViewModel
import kotlin.getValue

class DateScreenFragment: Fragment() {
    private val dateScreenViewModel: DateScreenViewModel by viewModel()

    companion object {
        const val CITY_WEATHER_KEY = "city_weather"
        const val DATE_WEATHER_KEY = "date_weather"

        fun newInstance(cityId: String, date: String): DateScreenFragment {
            val fragment = DateScreenFragment()
            val args = Bundle()
            args.putString(CITY_WEATHER_KEY, cityId)
            args.putString(DATE_WEATHER_KEY, date)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val cityId = arguments?.getString(CITY_WEATHER_KEY)
        cityId?.let {
            dateScreenViewModel.setCity(it)
        }
        arguments?.getString(DATE_WEATHER_KEY)?.let {
            dateScreenViewModel.setDate(it)
        }

        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                VSUWeatherTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        DateScreen(
                            modifier = Modifier.padding(innerPadding),
                            dateScreenViewModel = dateScreenViewModel,
                            navigateUp = {
                                (activity as? MainActivity)?.navigateTo(
                                    cityId?.let {
                                        ScreenConfig.CityScreenConfig(it)
                                    } ?: ScreenConfig.MainScreenConfig
                                )
                            }
                        )
                    }
                }
            }
        }
    }
}