package ru.mishbanya.vsuweather

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import ru.mishbanya.vsuweather.presentation.view.screens.city.CityScreenFragment
import ru.mishbanya.vsuweather.presentation.view.screens.main.MainScreenFragment
import ru.mishbanya.vsuweather.presentation.nav.ScreenConfig
import ru.mishbanya.vsuweather.presentation.view.screens.date.DateScreenFragment

class MainActivity : FragmentActivity() {
    private var screenConfig: ScreenConfig = ScreenConfig.MainScreenConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        navigateTo(screenConfig)
    }


    fun navigateTo(screenConfig: ScreenConfig){
        this.screenConfig = screenConfig
        when(screenConfig){
            is ScreenConfig.MainScreenConfig -> {
                supportFragmentManager.popBackStack(null, androidx.fragment.app.FragmentManager.POP_BACK_STACK_INCLUSIVE)
                supportFragmentManager.beginTransaction()
                    .setTransition(TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.fragment_container, MainScreenFragment())
                    .commit()
            }
            is ScreenConfig.CityScreenConfig -> {
                supportFragmentManager.beginTransaction()
                    .setTransition(TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.fragment_container, CityScreenFragment.newInstance(screenConfig.cityID))
                    .addToBackStack(null)
                    .commit()
            }
            is ScreenConfig.DateScreenConfig -> {
                supportFragmentManager.beginTransaction()
                    .setTransition(TRANSIT_FRAGMENT_FADE)
                    .replace(R.id.fragment_container, DateScreenFragment.newInstance(screenConfig.cityID, screenConfig.date))
                    .addToBackStack(null)
                    .commit()
            }
        }
    }
}