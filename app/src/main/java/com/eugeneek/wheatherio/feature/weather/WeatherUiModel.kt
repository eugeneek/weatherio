package com.eugeneek.wheatherio.feature.weather


data class WeatherUiModel(
    val place: String,
    val title: String,
    val weatherIconRes: Int,
    val temperature: String,
    val windSpeed: String,
    val windDirection: String,
    val humidity: String,
    val cloudiness: String,
    val pressure: String,
    val forecast: List<ForecastUiModel>
) {
    data class ForecastUiModel(
        val date: String,
        val weatherIconRes: Int,
        val temperature: String,
        val color: Int
    )
}