package com.eugeneek.wheatherio.feature.weather.data

import com.eugeneek.wheatherio.feature.app.Config
import timber.log.Timber
import java.io.File


class WeatherCacheManager(
    private val cacheFolder: File
) {

    fun saveWeather(cityId: Int, json: String) {
        saveData(getCacheFile(FOLDER_WEATHER, cityId), json)
    }

    fun saveForecast(cityId: Int, json: String) {
        saveData(getCacheFile(FOLDER_FORECAST, cityId), json)
    }

    fun getWeather(cityId: Int): String {
        return getCacheFile(FOLDER_WEATHER, cityId).readLines().getOrNull(1) ?: ""
    }

    fun getForecast(cityId: Int): String {
        return getCacheFile(FOLDER_FORECAST, cityId).readLines().getOrNull(1) ?: ""
    }

    fun isWeatherCacheValid(cityId: Int) = isCacheValid(FOLDER_WEATHER, cityId)

    fun isForecastCacheValid(cityId: Int) = isCacheValid(FOLDER_FORECAST, cityId)

    private fun isCacheValid(type: String, cityId: Int): Boolean {
        val file = getCacheFile(type, cityId)
        if (!file.exists()) {
            return false
        }

        val dt = file.readLines().firstOrNull() ?: "0"
        val cacheTime = System.currentTimeMillis() - dt.toLong()

        Timber.i("Cache time: $cacheTime")
        return cacheTime < Config.CACHE_INVALIDATE_PERIOD
    }

    private fun saveData(file: File, data: String) {
        file.writeText("${System.currentTimeMillis()}\n$data")
    }

    private fun getCacheFile(type: String, cityId: Int) = File(getCacheFolder(type), "$cityId.json")
        .apply { parentFile.mkdirs() }

    private fun getCacheFolder(type: String) = File(cacheFolder, type)

    companion object {
        const val FOLDER_WEATHER = "weather"
        const val FOLDER_FORECAST = "forecast"
    }
}