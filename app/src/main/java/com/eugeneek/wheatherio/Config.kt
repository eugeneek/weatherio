package com.eugeneek.wheatherio

object Config {
    const val API_BASE_URL = "http://api.openweathermap.org"
    const val API_KEY = "insert-your-api-key-here"

    const val CACHE_INVALIDATE_PERIOD = 3*60*60*1000    // 3 hours in milliseconds
}