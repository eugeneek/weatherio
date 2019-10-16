package com.eugeneek.wheatherio.feature.weather.domain

import com.eugeneek.wheatherio.feature.weather.data.network.ForecastResponse
import com.eugeneek.wheatherio.feature.weather.data.network.WeatherResponse
import com.eugeneek.wheatherio.feature.weather.domain.model.Forecast
import com.eugeneek.wheatherio.feature.weather.domain.model.Weather
import com.google.gson.Gson
import java.util.*


class WeatherDomainMapper(
    private val weatherMapper: WeatherMapper,
    private val gson: Gson
) {

    fun mapWeatherResponseStr(responseStr: String): Weather {
        val response = gson.fromJson(responseStr, WeatherResponse::class.java)

        return mapWeatherResponse(response)
    }

    fun mapForecastResponse(responseStr: String): Forecast {
        val response = gson.fromJson(responseStr, ForecastResponse::class.java)

        val forecastData = mutableListOf<Weather>()
        for (weatherResponse in response.forecastList) {
            val weather = mapWeatherResponse(weatherResponse)
            forecastData.add(weather)
        }

        val city = Forecast.City(
            name = response.cityDataResponse.name,
            countryCode = response.cityDataResponse.countryCode,
            sunrise = Date(response.cityDataResponse.sunrise*1000),
            sunset = Date(response.cityDataResponse.sunset*1000)
        )

        return Forecast(
            data = forecastData,
            city = city
        )
    }

    private fun mapWeatherResponse(response: WeatherResponse): Weather {
        val wind = Weather.Wind(
            speed = weatherMapper.getSpeedStr(response.windData.speed),
            direction = weatherMapper.getDirectionFromDegree(response.windData.degree)
        )

        val temperature = Weather.Temperature(
            current = weatherMapper.getDegreeStr(response.weatherData.temp),
            min = weatherMapper.getDegreeStr(response.weatherData.tempMin),
            max = weatherMapper.getDegreeStr(response.weatherData.tempMax)
        )

        return Weather(
            title = weatherMapper.weatherTitleByCode(response.overviewList.first().code),
            description = response.overviewList.first().description,
            iconRes = weatherMapper.weatherIconByCode(response.overviewList.first().code),
            temperature = temperature,
            pressure = weatherMapper.getPressureStr(response.weatherData.pressure),
            humidity = weatherMapper.getPercentStr(response.weatherData.humidity),
            cloudiness = weatherMapper.getPercentStr(response.cloudsData.cloudiness),
            wind = wind,
            date = Date(response.date*1000)
        )
    }
}