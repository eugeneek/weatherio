package com.eugeneek.wheatherio.feature.weather.data

import com.eugeneek.wheatherio.feature.weather.domain.WeatherDomainMapper
import com.eugeneek.wheatherio.feature.weather.domain.model.Forecast
import com.eugeneek.wheatherio.feature.weather.domain.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import timber.log.Timber


class WeatherRepository(
    private val localDataSource: WeatherDataSource,
    private val remoteDataSource: WeatherDataSource,
    private val weatherCacheManager: WeatherCacheManager,
    private val weatherDomainMapper: WeatherDomainMapper
) {

    suspend fun getWeatherByCity(cityId: Int): Weather = withContext(Dispatchers.IO) {
        Timber.i("CALL | WeatherRepository.getWeatherByCity($cityId)")
        val weatherResponse: String
        if (weatherCacheManager.isWeatherCacheValid(cityId)) {
            weatherResponse = localDataSource.getCurrentWeather(cityId)
        } else {
            weatherResponse = remoteDataSource.getCurrentWeather(cityId)
            weatherCacheManager.saveWeather(cityId, weatherResponse)
        }

        return@withContext weatherDomainMapper.mapWeatherResponseStr(weatherResponse)
    }

    suspend fun getForecastByCity(cityId: Int): Forecast = withContext(Dispatchers.IO) {
        Timber.i("CALL | WeatherRepository.getForecastByCity($cityId)")
        val forecastResponse: String
        if (weatherCacheManager.isForecastCacheValid(cityId)) {
            forecastResponse = localDataSource.getForecast(cityId)
        } else {
            forecastResponse = remoteDataSource.getForecast(cityId)
            weatherCacheManager.saveForecast(cityId, forecastResponse)
        }

        return@withContext weatherDomainMapper.mapForecastResponse(forecastResponse)
    }
}