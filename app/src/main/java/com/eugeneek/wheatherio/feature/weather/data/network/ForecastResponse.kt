package com.eugeneek.wheatherio.feature.weather.data.network

import com.google.gson.annotations.SerializedName


data class ForecastResponse(
    @SerializedName("list") val forecastList: List<WeatherResponse>,
    @SerializedName("city") val cityDataResponse: CityDataResponse
)

data class CityDataResponse(
    @SerializedName("id") val id: Long,
    @SerializedName("name") val name: String,
    @SerializedName("country") val countryCode: String,
    @SerializedName("timezone") val timezone: Int,
    @SerializedName("sunrise") val sunrise: Long,
    @SerializedName("sunset") val sunset: Long
)