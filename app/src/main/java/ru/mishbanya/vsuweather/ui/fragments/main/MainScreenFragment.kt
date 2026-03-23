package ru.mishbanya.vsuweather.ui.fragments.main

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
import ru.mishbanya.vsuweather.MainActivity
import ru.mishbanya.vsuweather.domain.vm.MainScreenViewModel
import ru.mishbanya.vsuweather.ui.theme.VSUWeatherTheme

class MainScreenFragment : Fragment() {
    private val mainScreenViewModel: MainScreenViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                VSUWeatherTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        MainScreen(
                            modifier = Modifier.padding(innerPadding),
                            mainScreenViewModel = mainScreenViewModel,
                            navigateTo = { screenConfig ->
                                (activity as? MainActivity)?.navigateTo(screenConfig)
                            }
                        )
                    }
                }
            }
        }
    }
}