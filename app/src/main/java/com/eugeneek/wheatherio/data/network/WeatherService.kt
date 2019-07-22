package com.eugeneek.wheatherio.data.network

import com.eugeneek.wheatherio.Config
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getWeatherByCityAsync(
        @Query("q") cityName: String,
        @Query("units") units: String = "metric",
        @Query("APPID") appId: String = Config.API_KEY
    ): Deferred<WeatherResponse>


}