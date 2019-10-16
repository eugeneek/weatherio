package com.eugeneek.wheatherio.feature.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eugeneek.wheatherio.feature.weather.domain.WeatherUseCase
import kotlinx.coroutines.launch


class WeatherPresenter(
    private val weatherUseCase: WeatherUseCase,
    private val weatherUiMapper: WeatherUiMapper
): ViewModel() {

    private val _weatherUiModel = MutableLiveData<WeatherUiModel>()
    val weatherUiModel: LiveData<WeatherUiModel>
        get() = _weatherUiModel

    private val _loadingUiModel = MutableLiveData<Boolean>()
    val loadingUiModel: LiveData<Boolean>
        get() = _loadingUiModel

    init {
        viewModelScope.launch {
            _loadingUiModel.value = true
            val weatherModel = weatherUseCase.getWeatherExtendedData()
            val weatherUiModel = weatherUiMapper.fromDomainModel(weatherModel)

            _loadingUiModel.value = false
            _weatherUiModel.value = weatherUiModel
        }
    }

}