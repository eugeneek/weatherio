package com.eugeneek.wheatherio.feature.weather.data.network

import com.google.gson.annotations.SerializedName


data class WeatherResponse(
    @SerializedName("weather") val overviewList: List<OverviewResponse>,
    @SerializedName("main") val weatherData: WeatherDataResponse,
    @SerializedName("wind") val windData: WindDataResponse,
    @SerializedName("clouds") val cloudsData: CloudsDataResponse,
    @SerializedName("id") val cityId: Long,
    @SerializedName("name") val cityName: String,
    @SerializedName("dt") val date: Long
    )

data class OverviewResponse(
    @SerializedName("id") val code: Int,
    @SerializedName("main") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("icon") val icon: String
)

data class WeatherDataResponse(
    @SerializedName("temp") val temp: Float,
    @SerializedName("pressure") val pressure: Float,
    @SerializedName("humidity") val humidity: Float,
    @SerializedName("temp_min") val tempMin: Float,
    @SerializedName("temp_max") val tempMax: Float
)

data class WindDataResponse(
    @SerializedName("speed") val speed: Float,
    @SerializedName("deg") val degree: Float
)

data class CloudsDataResponse(
    @SerializedName("all") val cloudiness: Float
)