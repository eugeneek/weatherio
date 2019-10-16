package com.eugeneek.wheatherio.feature.weather.data


class WeatherLocalDataSource(
    private val weatherCacheManager: WeatherCacheManager
): WeatherDataSource {

    override suspend fun getCurrentWeather(cityId: Int) = weatherCacheManager.getWeather(cityId)

    override suspend fun getForecast(cityId: Int) = weatherCacheManager.getForecast(cityId)

}