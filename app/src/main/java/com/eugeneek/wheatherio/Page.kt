package com.eugeneek.wheatherio

import androidx.fragment.app.Fragment
import com.eugeneek.wheatherio.feature.weather.WeatherFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Page {

    class Weather: SupportAppScreen() {
        override fun getFragment(): Fragment {
            return WeatherFragment.create()
        }
    }
}