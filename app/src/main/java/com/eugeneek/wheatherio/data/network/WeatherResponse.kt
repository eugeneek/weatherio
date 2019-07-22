package com.eugeneek.wheatherio.data.network

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("weather") val overviewList: List<OverviewResponse>,
    @SerializedName("main") val weatherData: WeatherDataResponse,
    @SerializedName("wind") val windData: WindDataResponse,
    @SerializedName("id") val cityId: Long,
    @SerializedName("name") val cityName: String,
    @SerializedName("dt") val date: Long
    )

data class OverviewResponse(
    @SerializedName("id") val id: Int,
    @SerializedName("main") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class WeatherDataResponse(
    @SerializedName("temp") val temp: Float,
    @SerializedName("pressure") val pressure: Int,
    @SerializedName("humidity") val humidity: Int,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float
)

data class WindDataResponse(
    @SerializedName("speed") val speed: Float,
    @SerializedName("deg") val degree: Int
)