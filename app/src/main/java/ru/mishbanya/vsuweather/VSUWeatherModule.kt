package ru.mishbanya.vsuweather

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import ru.mishbanya.vsuweather.data.common.CommonModule
import ru.mishbanya.vsuweather.data.database.DatabaseModule

@Module(
    includes = [
        CommonModule::class,
        DatabaseModule::class,
    ]
)
@ComponentScan
class VSUWeatherModule