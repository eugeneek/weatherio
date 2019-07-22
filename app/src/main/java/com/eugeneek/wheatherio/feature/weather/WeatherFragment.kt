package com.eugeneek.wheatherio.feature.weather


import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import com.eugeneek.wheatherio.R
import com.eugeneek.wheatherio.feature.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_weather.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class WeatherFragment : BaseFragment() {
    override val layoutRes = R.layout.fragment_weather

    private val presenter: WeatherPresenter by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.getWeatherLiveData().observe(viewLifecycleOwner, Observer { weather ->
            tvWeather.text = weather.toString()
        })
    }

    companion object {
        fun create() = WeatherFragment()
    }
}
