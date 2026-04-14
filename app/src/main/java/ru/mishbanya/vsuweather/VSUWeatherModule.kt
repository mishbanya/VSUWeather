package ru.mishbanya.vsuweather

import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single
import retrofit2.Retrofit
import ru.mishbanya.vsuweather.data.common.CommonModule

@Module(
    includes = [CommonModule::class]
)
@ComponentScan
class VSUWeatherModule