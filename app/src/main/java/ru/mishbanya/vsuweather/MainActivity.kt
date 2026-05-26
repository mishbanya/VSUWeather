package ru.mishbanya.vsuweather

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction.TRANSIT_FRAGMENT_FADE
import ru.mishbanya.vsuweather.notifications.MockNotificationService
import ru.mishbanya.vsuweather.presentation.nav.ScreenConfig
import ru.mishbanya.vsuweather.presentation.view.screens.city.CityScreenFragment
import ru.mishbanya.vsuweather.presentation.view.screens.date.DateScreenFragment
import ru.mishbanya.vsuweather.presentation.view.screens.main.MainScreenFragment

class MainActivity : FragmentActivity() {
    private var screenConfig: ScreenConfig = ScreenConfig.MainScreenConfig

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        navigateTo(screenConfig)
        requestOrStartNotificationService()
    }

    private fun requestOrStartNotificationService() {
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) &&
            (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED)
        ) {
            registerForActivityResult(ActivityResultContracts.RequestPermission()) { granted ->
                if (granted) startNotificationService()
            }
        } else {
            startNotificationService()
        }
    }

    private fun startNotificationService() {
        val intent = Intent(this, MockNotificationService::class.java)
        startForegroundService(intent)
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