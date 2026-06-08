package ru.mishbanya.vsuweather.notifications

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.IBinder
import androidx.core.app.NotificationCompat
import androidx.glance.appwidget.updateAll
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlin.random.Random
import ru.mishbanya.vsuweather.widget.WeatherWidget

class MockNotificationService : Service() {

    private val serviceScope = CoroutineScope(Dispatchers.Default)
    private var loopJob: Job? = null

    override fun onCreate() {
        super.onCreate()
        createChannels()
        startForeground(FOREGROUND_NOTIFICATION_ID, buildForegroundNotification())

        loopJob = serviceScope.launch {
            WeatherEventBus.weatherUpdated.collect {
                postMockNotification()
                WeatherWidget().updateAll(this@MockNotificationService)
            }
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int = START_STICKY

    override fun onDestroy() {
        super.onDestroy()
        loopJob?.cancel()
        serviceScope.cancel()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun postMockNotification() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        val notification = NotificationCompat.Builder(this, MOCK_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("Mock Push")
            .setContentText("Погода была успешно обновлена")
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setAutoCancel(true)
            .build()
        manager.notify(Random.nextInt(), notification)
    }

    private fun buildForegroundNotification(): Notification =
        NotificationCompat.Builder(this, FOREGROUND_CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_dialog_info)
            .setContentTitle("VSUWeather")
            .setContentText("Сервис уведомлений активен")
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setOngoing(true)
            .build()

    private fun createChannels() {
        val manager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        manager.createNotificationChannel(
            NotificationChannel(FOREGROUND_CHANNEL_ID, "Сервис уведомлений", NotificationManager.IMPORTANCE_LOW)
        )
        manager.createNotificationChannel(
            NotificationChannel(MOCK_CHANNEL_ID, "Mock Push", NotificationManager.IMPORTANCE_HIGH)
        )
    }

    companion object {
        private const val FOREGROUND_NOTIFICATION_ID = 1001
        private const val FOREGROUND_CHANNEL_ID = "foreground_service_channel"
        private const val MOCK_CHANNEL_ID = "mock_push_channel"
        private const val INTERVAL_MS = 10_000L
    }
}
