package com.eugeneek.wheatherio.data

import com.eugeneek.wheatherio.data.db.WeatherEntity
import com.eugeneek.wheatherio.data.network.WeatherResponse
import java.util.*


class WeatherMapper {

    fun mapWeatherResponse(weatherResponse: WeatherResponse): Weather {
        val wind = Wind(
            speed = weatherResponse.windData.speed,
            direction = getDirectionFromDegree(weatherResponse.windData.degree)
            )

        val temperature = Temperature(
            current = weatherResponse.weatherData.temp,
            tempMin = weatherResponse.weatherData.tempMin,
            tempMax = weatherResponse.weatherData.tempMax
        )

        return Weather(
            title = weatherResponse.overviewList.first().title,
            description = weatherResponse.overviewList.first().description,
            temperature = temperature,
            pressure = weatherResponse.weatherData.pressure,
            humidity = weatherResponse.weatherData.humidity,
            wind = wind
        )
    }

    fun mapWeatherCache(weatherCache: WeatherEntity): Weather {
        val wind = Wind(
            speed = weatherCache.windSpeed,
            direction = getDirectionFromDegree(weatherCache.windDegree)
        )

        val temperature = Temperature(
            current = weatherCache.temperatureCurrent,
            tempMin = weatherCache.temperatureMin,
            tempMax = weatherCache.temperatureMax
        )

        return Weather(
            title = weatherCache.weatherTitle,
            description = weatherCache.weatherDescription,
            temperature = temperature,
            pressure = weatherCache.pressure,
            humidity = weatherCache.humidity,
            wind = wind
        )
    }

    fun mapWeatherResponseToCache(weatherResponse: WeatherResponse): WeatherEntity {
        return WeatherEntity(
            cityId = weatherResponse.cityId,
            cityName = weatherResponse.cityName,
            weatherTitle = weatherResponse.overviewList.first().title,
            weatherDescription = weatherResponse.overviewList.first().description,
            temperatureCurrent = weatherResponse.weatherData.temp,
            temperatureMin = weatherResponse.weatherData.tempMin,
            temperatureMax = weatherResponse.weatherData.tempMax,
            humidity = weatherResponse.weatherData.humidity,
            pressure = weatherResponse.weatherData.pressure,
            windSpeed = weatherResponse.windData.speed,
            windDegree = weatherResponse.windData.degree,
            updatedAt = Date()
        )
    }

    private fun getDirectionFromDegree(degree: Int) = when (degree) {
        in 338..360, in 0..22 -> "N"
        in 23..67 -> "NE"
        in 68..112 -> "E"
        in 113..157 -> "SE"
        in 158..202 -> "S"
        in 203..247 -> "SW"
        in 248..292 -> "W"
        in 293..337 -> "NW"
        else -> "N/A"
    }
}