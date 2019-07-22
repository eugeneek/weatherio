package com.eugeneek.wheatherio.data


data class Weather(
    val title: String,
    val description: String,
    val temperature: Temperature,
    val pressure: Int,
    val humidity: Int,
    val wind: Wind

)

data class Wind(
    val speed: Float,
    val direction: String
)

data class Temperature(
    val current: Float,
    val tempMin: Float,
    val tempMax: Float
)