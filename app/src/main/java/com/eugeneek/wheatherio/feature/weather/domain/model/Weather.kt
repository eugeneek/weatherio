package com.eugeneek.wheatherio.feature.weather.domain.model

import java.util.*


data class Weather(
    val title: String,
    val description: String,
    val iconRes: Int,
    val temperature: Temperature,
    val pressure: String,
    val humidity: String,
    val cloudiness: String,
    val wind: Wind,
    val date: Date
) {

    data class Wind(
        val speed: String,
        val direction: String
    )

    data class Temperature(
        val current: String,
        val min: String,
        val max: String
    )
}