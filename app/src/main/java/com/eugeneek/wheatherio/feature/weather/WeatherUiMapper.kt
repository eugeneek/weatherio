package com.eugeneek.wheatherio.feature.weather

import android.text.format.DateUtils
import com.eugeneek.wheatherio.R
import com.eugeneek.wheatherio.feature.core.domain.ResourceManager
import com.eugeneek.wheatherio.feature.weather.domain.WeatherMapper
import com.eugeneek.wheatherio.feature.weather.domain.model.WeatherExtendedData
import java.text.DateFormat


class WeatherUiMapper(
    private val weatherMapper: WeatherMapper,
    private val resourceManager: ResourceManager,
    private val dateFormatter: DateFormat
) {
    fun fromDomainModel(model: WeatherExtendedData): WeatherUiModel {
        val place = "${model.forecast.city.name}, ${model.forecast.city.countryCode}"

        val forecastList = mutableListOf<WeatherUiModel.ForecastUiModel>()
        for (forecastWeather in model.forecast.data) {
            val dateStr = dateFormatter.format(forecastWeather.date)
            val color = if (DateUtils.isToday(forecastWeather.date.time)) {
                resourceManager.getColor(R.color.textDark)
            } else {
                resourceManager.getColor(R.color.textSemiDark)
            }
            val forecastUiModel = WeatherUiModel.ForecastUiModel(
                date = dateStr,
                weatherIconRes = forecastWeather.iconRes,
                temperature = forecastWeather.temperature.current,
                color = color
            )

            forecastList.add(forecastUiModel)
        }

        return WeatherUiModel(
            place = place,
            title = model.currentWeather.title,
            weatherIconRes = model.currentWeather.iconRes,
            temperature = model.currentWeather.temperature.current,
            windSpeed = model.currentWeather.wind.speed,
            windDirection = model.currentWeather.wind.direction,
            humidity = model.currentWeather.humidity,
            cloudiness = model.currentWeather.cloudiness,
            pressure = model.currentWeather.pressure,
            forecast = forecastList
        )
    }
}