package com.eugeneek.wheatherio.feature.weather.data

interface WeatherDataSource {
    suspend fun getCurrentWeather(cityId: Int): String
    suspend fun getForecast(cityId: Int): String
}