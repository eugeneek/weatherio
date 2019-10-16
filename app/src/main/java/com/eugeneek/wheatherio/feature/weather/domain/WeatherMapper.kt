package com.eugeneek.wheatherio.feature.weather.domain

import com.eugeneek.wheatherio.R
import com.eugeneek.wheatherio.feature.core.domain.ResourceManager
import kotlin.math.roundToInt


class WeatherMapper(
    private val resourceManager: ResourceManager
) {

    fun weatherTitleByCode(weatherCode: Int): String {
        return when(weatherCode) {
            in 200..299 -> resourceManager.getString(R.string.weather_thunderstorm)
            in 300..399 -> resourceManager.getString(R.string.weather_drizzle)
            in 500..599 -> resourceManager.getString(R.string.weather_rain)
            in 600..699 -> resourceManager.getString(R.string.weather_snow)
            701 -> resourceManager.getString(R.string.weather_mist)
            711 -> resourceManager.getString(R.string.weather_smoke)
            721 -> resourceManager.getString(R.string.weather_haze)
            731, 761 -> resourceManager.getString(R.string.weather_dust)
            741 -> resourceManager.getString(R.string.weather_fog)
            751 -> resourceManager.getString(R.string.weather_sand)
            762 -> resourceManager.getString(R.string.weather_ash)
            771 -> resourceManager.getString(R.string.weather_squall)
            781 -> resourceManager.getString(R.string.weather_tornado)
            800 -> resourceManager.getString(R.string.weather_clear)
            in 801..804 -> resourceManager.getString(R.string.weather_clouds)
            else -> resourceManager.getString(R.string.weather_unknown)
        }
    }

    fun weatherIconByCode(weatherCode: Int): Int {
        return when(weatherCode) {
            in 200..299 -> R.drawable.ic_thunderstorm
            in 300..399 -> R.drawable.ic_drizzle

            500 -> R.drawable.ic_rain_light
            501 -> R.drawable.ic_rain_moderate
            in 502..504 -> R.drawable.ic_rain_heavy
            511 -> R.drawable.ic_rain_freezing
            in 520..599 -> R.drawable.ic_rain_light

            in 600..601 -> R.drawable.ic_snow_light
            602 -> R.drawable.ic_snow_heavy
            in 611..699 -> R.drawable.ic_rain_freezing

            in 701..771 -> R.drawable.ic_fog
            781 -> R.drawable.ic_tornado

            800 -> R.drawable.ic_clear
            in 801..802 -> R.drawable.ic_clouds_few
            in 803..804 -> R.drawable.ic_clouds_overcast
            else -> R.drawable.ic_clouds_overcast
        }
    }

    fun getDirectionFromDegree(degree: Float) = when (degree) {
        in 338f..360f, in 0f..22f -> "N"
        in 23f..67f -> "NE"
        in 68f..112f -> "E"
        in 113f..157f -> "SE"
        in 158f..202f -> "S"
        in 203f..247f -> "SW"
        in 248f..292f -> "W"
        in 293f..337f -> "NW"
        else -> "N/A"
    }

    fun getSpeedStr(value: Float) = "${value.roundToInt()} ${resourceManager.speedSign}"
    fun getDegreeStr(value: Float) = "${value.roundToInt()}${resourceManager.degreeSign}"
    fun getPercentStr(value: Float) = "${value.roundToInt()}${resourceManager.percentSign}"
    fun getPressureStr(value: Float) = "${(value*0.75006375541921).roundToInt()} ${resourceManager.pressureSign}"
}