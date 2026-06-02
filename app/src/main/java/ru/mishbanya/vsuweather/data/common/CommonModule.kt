package ru.mishbanya.vsuweather.data.common

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import ru.mishbanya.vsuweather.data.database.AppDatabase

@Module
class CommonModule {
    private val SP_KEY = "vsu_weather_key"
    //private val BASE_URL = "https://api-labs.wiremockapi.cloud/"
    private val BASE_URL = "https://weather-forecast-vsu.free.beeceptor.com/"

    @Single
    fun provideRetrofitClient(): Retrofit {
        val networkJson = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(networkJson.asConverterFactory("application/json".toMediaType()))
            .build()
    }

    @Single
    fun provideAppDatabase(context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java,
        "vsu_weather_database"
    ).build()

    @Single
    fun provideSharedPreferences(
        context: Context
    ): SharedPreferences = context.getSharedPreferences(SP_KEY, Context.MODE_PRIVATE)
}