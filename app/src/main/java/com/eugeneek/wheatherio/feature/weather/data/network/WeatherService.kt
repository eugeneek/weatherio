package com.eugeneek.wheatherio.feature.weather.data.network

import com.eugeneek.wheatherio.feature.app.Config
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("id") cityId: Int,
        @Query("units") units: String = "metric",
        @Query("APPID") appId: String = Config.API_KEY
    ): String

    @GET("/data/2.5/forecast")
    suspend fun getForecastByCity(
        @Query("id") cityId: Int,
        @Query("units") units: String = "metric",
        @Query("APPID") appId: String = Config.API_KEY
    ): String


}