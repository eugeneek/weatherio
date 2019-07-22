package com.eugeneek.wheatherio.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface WeatherDao {
    @Query("SELECT * FROM WeatherEntity WHERE cityName = :cityName LIMIT 1")
    suspend fun getWeatherByCity(cityName: String): WeatherEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun setWeather(weatherEntity: WeatherEntity)
}