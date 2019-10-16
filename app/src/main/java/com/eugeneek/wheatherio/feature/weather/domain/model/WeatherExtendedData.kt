package com.eugeneek.wheatherio.feature.weather.domain.model


data class WeatherExtendedData(
    val currentWeather: Weather,
    val forecast: Forecast
)