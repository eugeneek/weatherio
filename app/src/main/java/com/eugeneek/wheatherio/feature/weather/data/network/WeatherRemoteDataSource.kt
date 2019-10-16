package com.eugeneek.wheatherio.feature.weather.data.network

import com.eugeneek.wheatherio.feature.weather.data.WeatherDataSource


class WeatherRemoteDataSource(
    private val service: WeatherService
): WeatherDataSource {

    override suspend fun getCurrentWeather(cityId: Int) = service.getWeatherByCity(cityId)

    override suspend fun getForecast(cityId: Int) = service.getForecastByCity(cityId)

}