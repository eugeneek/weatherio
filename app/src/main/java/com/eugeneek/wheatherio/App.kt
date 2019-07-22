package com.eugeneek.wheatherio

import android.app.Application
import androidx.room.Room
import com.eugeneek.wheatherio.data.WeatherMapper
import com.eugeneek.wheatherio.data.db.AppDatabase
import com.eugeneek.wheatherio.data.network.WeatherService
import com.eugeneek.wheatherio.feature.base.SettingsProvider
import com.eugeneek.wheatherio.feature.weather.WeatherPresenter
import com.facebook.stetho.Stetho
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.core.logger.Level
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Router
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogging()
        initDi()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initDi() {

        val appModule = module {
            val httpClient = OkHttpClient.Builder()
                .addInterceptor(HttpLoggingInterceptor { message -> Timber.d(message) }.apply { level = HttpLoggingInterceptor.Level.BODY })
                .build()
            val weatherService = Retrofit.Builder()
                .baseUrl(Config.API_BASE_URL)
                .client(httpClient)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WeatherService::class.java)

            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "database-name")
                .build()
            val weatherDao = db.weatherDao()

            val weatherMapper = WeatherMapper()

            val weatherRepository = WeatherRepository(weatherService, weatherDao, weatherMapper)

            val cicerone: Cicerone<Router> = Cicerone.create()

            single { weatherRepository }
            single { cicerone.router }
            single { cicerone.navigatorHolder }
            single { SettingsProvider() }

            viewModel { WeatherPresenter(get(), get()) }
        }

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@App)
            modules(appModule)
        }
    }
}