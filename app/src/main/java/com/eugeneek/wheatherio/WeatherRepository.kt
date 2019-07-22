package com.eugeneek.wheatherio

import com.eugeneek.wheatherio.data.Weather
import com.eugeneek.wheatherio.data.WeatherMapper
import com.eugeneek.wheatherio.data.db.WeatherDao
import com.eugeneek.wheatherio.data.db.WeatherEntity
import com.eugeneek.wheatherio.data.network.WeatherResponse
import com.eugeneek.wheatherio.data.network.WeatherService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber
import java.util.*


class WeatherRepository(
    private val weatherService: WeatherService,
    private val weatherDao: WeatherDao,
    private val weatherMapper: WeatherMapper
) {

    suspend fun getWeatherByCity(cityName: String): Weather = withContext(Dispatchers.IO) {
        Timber.i("CALL | WeatherRepository.getWeatherByCity($cityName)")
        val cachedWeather = getCachedWeatherByCity(cityName)
        if (cachedWeather != null) {
            val weather = weatherMapper.mapWeatherCache(cachedWeather)
            Timber.i("Mapped weather:\n$weather")

            return@withContext weather
        } else {
            val weatherResponse = weatherService.getWeatherByCityAsync(cityName).await()
            Timber.i("Response  weather:\n$weatherResponse")
            saveWeatherToCache(weatherResponse)

            val weather = weatherMapper.mapWeatherResponse(weatherResponse)
            Timber.i("Mapped weather:\n$weather")

            return@withContext weather
        }
    }

    private suspend fun getCachedWeatherByCity(cityName: String): WeatherEntity? = withContext(Dispatchers.IO) {
        val cachedWeather = weatherDao.getWeatherByCity(cityName)
        Timber.i("Cached weather:\n$cachedWeather")

        return@withContext if (isWeatherCacheValid(cachedWeather)) {
            Timber.d("Weather taken from cache")
             cachedWeather
        } else {
            Timber.d("Weather cache not valid")
            null
        }
    }

    private fun isWeatherCacheValid(weatherEntity: WeatherEntity?): Boolean {
        return if (weatherEntity == null) {
            Timber.d("Weather cache not persist")
            false
        } else {
            val cacheValidDate = Calendar.getInstance().apply { add(Calendar.MILLISECOND, -Config.CACHE_INVALIDATE_PERIOD) }
            weatherEntity.updatedAt.after(cacheValidDate.time)
        }
    }

    private suspend fun saveWeatherToCache(weatherResponse: WeatherResponse) {
        withContext(Dispatchers.IO) {
            val weatherEntity = weatherMapper.mapWeatherResponseToCache(weatherResponse)
            weatherDao.setWeather(weatherEntity)
        }
    }
}