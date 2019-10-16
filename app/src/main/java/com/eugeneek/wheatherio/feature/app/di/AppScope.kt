package com.eugeneek.wheatherio.feature.app.di

import android.content.Context
import com.eugeneek.injector.Scope
import com.eugeneek.wheatherio.feature.app.Config
import com.eugeneek.wheatherio.feature.core.data.SettingsProvider
import com.eugeneek.wheatherio.feature.core.domain.ResourceManager
import com.eugeneek.wheatherio.feature.weather.WeatherUiMapper
import com.eugeneek.wheatherio.feature.weather.data.WeatherCacheManager
import com.eugeneek.wheatherio.feature.weather.data.WeatherLocalDataSource
import com.eugeneek.wheatherio.feature.weather.data.WeatherRepository
import com.eugeneek.wheatherio.feature.weather.data.network.WeatherRemoteDataSource
import com.eugeneek.wheatherio.feature.weather.data.network.WeatherService
import com.eugeneek.wheatherio.feature.weather.domain.WeatherDomainMapper
import com.eugeneek.wheatherio.feature.weather.domain.WeatherMapper
import com.eugeneek.wheatherio.feature.weather.domain.WeatherUseCase
import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.scalars.ScalarsConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber
import java.text.SimpleDateFormat
import java.util.*


class AppScope(
    private val context: Context
): Scope() {

    override fun init() {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
                    override fun log(message: String) { Timber.d(message) }
                })
                    .apply { level = HttpLoggingInterceptor.Level.BODY }
            )
            .build()

        val weatherService = Retrofit.Builder()
            .baseUrl(Config.API_BASE_URL)
            .client(httpClient)
            .addConverterFactory(ScalarsConverterFactory.create())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherService::class.java)

        val gson = Gson()

        val resourceManager = ResourceManager(context.resources)

        val cacheManager =
            WeatherCacheManager(context.cacheDir)
        val weatherMapper =
            WeatherMapper(resourceManager)
        val weatherDomainMapper =
            WeatherDomainMapper(
                weatherMapper,
                gson
            )

        val weatherRepository = WeatherRepository(
            localDataSource = WeatherLocalDataSource(
                cacheManager
            ),
            remoteDataSource = WeatherRemoteDataSource(
                weatherService
            ),
            weatherCacheManager = cacheManager,
            weatherDomainMapper = weatherDomainMapper
        )

        val settingsProvider = SettingsProvider()
        val weatherUseCase = WeatherUseCase(
            settingsProvider,
            weatherRepository
        )
        val forecastDateFormatter = SimpleDateFormat("HH:mm\nEEE", Locale.getDefault())

        val weatherUiMapper = WeatherUiMapper(weatherMapper, resourceManager, forecastDateFormatter)

        val cicerone: Cicerone<Router> = Cicerone.create()

        bind(resourceManager)
        bind(weatherRepository)
        bind(cicerone.router)
        bind(cicerone.navigatorHolder)
        bind(settingsProvider)
        bind(weatherUseCase)
        bind(weatherUiMapper)
    }
}