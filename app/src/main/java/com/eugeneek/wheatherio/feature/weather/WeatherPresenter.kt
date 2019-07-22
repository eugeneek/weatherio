package com.eugeneek.wheatherio.feature.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugeneek.wheatherio.WeatherRepository
import com.eugeneek.wheatherio.data.Weather
import com.eugeneek.wheatherio.feature.base.SettingsProvider
import kotlinx.coroutines.launch


class WeatherPresenter(
    private val weatherRepository: WeatherRepository,
    private val settingsProvider: SettingsProvider
): ViewModel() {

    init {
        viewModelScope.launch {
            val cityName = settingsProvider.getCurrentCity()
            val weather = weatherRepository.getWeatherByCity(cityName)
            weatherLiveData.value = weather
        }
    }

    private val weatherLiveData = MutableLiveData<Weather>()

    fun getWeatherLiveData(): LiveData<Weather> = weatherLiveData

}