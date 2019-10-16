package com.eugeneek.wheatherio.feature.weather.domain

import com.eugeneek.wheatherio.feature.weather.data.WeatherRepository
import com.eugeneek.wheatherio.feature.weather.domain.model.WeatherExtendedData
import com.eugeneek.wheatherio.feature.core.data.SettingsProvider


class WeatherUseCase(
    private val settingsProvider: SettingsProvider,
    private val weatherRepository: WeatherRepository
) {

    suspend fun getWeatherExtendedData(): WeatherExtendedData {
        val cityId = settingsProvider.getCurrentCity()
        val weather = weatherRepository.getWeatherByCity(cityId)
        val forecast = weatherRepository.getForecastByCity(cityId)

        return WeatherExtendedData(
            currentWeather = weather,
            forecast = forecast
        )
    }
}