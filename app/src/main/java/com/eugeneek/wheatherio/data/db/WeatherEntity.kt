package com.eugeneek.wheatherio.data.db

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity
data class WeatherEntity(
    @PrimaryKey val cityId: Long,
    val cityName: String,
    val weatherTitle: String,
    val weatherDescription: String,
    val temperatureCurrent: Float,
    val temperatureMin: Float,
    val temperatureMax: Float,
    val windSpeed: Float,
    val windDegree: Int,
    val humidity: Int,
    val pressure: Int,
    val updatedAt: Date
)
