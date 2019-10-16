package com.eugeneek.wheatherio.feature.weather.ui


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.eugeneek.injector.Injector.get
import com.eugeneek.wheatherio.R
import com.eugeneek.wheatherio.feature.core.domain.viewModelProvider
import com.eugeneek.wheatherio.feature.core.ui.BaseFragment
import com.eugeneek.wheatherio.feature.weather.ForecastAdapter
import com.eugeneek.wheatherio.feature.weather.WeatherPresenter
import com.eugeneek.wheatherio.feature.weather.WeatherUiModel
import kotlinx.android.synthetic.main.fragment_weather.*


class WeatherFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_weather

    private val presenter: WeatherPresenter by viewModelProvider {
        WeatherPresenter(
            weatherUseCase = get(),
            weatherUiMapper = get()
        )
    }

    private val forecastAdapter = ForecastAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.weatherUiModel.observe(viewLifecycleOwner, Observer { weather ->
            setupWeatherView(weather)
        })

        presenter.loadingUiModel.observe(viewLifecycleOwner, Observer { showLoading(it) })
    }

    private fun showLoading(show: Boolean) {
        flLoading.visibility = if (show) View.VISIBLE else View.GONE
    }

    private fun setupWeatherView(weather: WeatherUiModel) {
        tvPlace.text = weather.place

        tvTitle.text = weather.title

        ivWeather.setImageResource(weather.weatherIconRes)

        tvTempCurrent.text = weather.temperature

        tvWindValue.text = weather.windSpeed
        tvWindDirection.text = weather.windDirection

        tvHumidityValue.text = weather.humidity

        tvCloudinessValue.text = weather.cloudiness

        tvPressureValue.text = weather.pressure

        rvForecast.apply {
            adapter = forecastAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        }

        forecastAdapter.submitList(weather.forecast)

        ItemSnapHelper().apply { attachToRecyclerView(rvForecast) }
    }

    companion object {
        fun create() = WeatherFragment()
    }
}
