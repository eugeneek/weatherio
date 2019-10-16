package com.eugeneek.wheatherio.feature.weather.domain.model

import java.util.*


data class Forecast(
    val data: List<Weather>,
    val city: City
) {
    data class City(
        val name: String,
        val countryCode: String,
        val sunrise: Date,
        val sunset: Date
    )
}