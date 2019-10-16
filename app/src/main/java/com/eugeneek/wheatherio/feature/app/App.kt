package com.eugeneek.wheatherio.feature.app

import android.app.Application
import com.eugeneek.injector.Injector
import com.eugeneek.wheatherio.BuildConfig
import com.eugeneek.wheatherio.feature.app.di.AppScope
import com.facebook.stetho.Stetho
import timber.log.Timber


class App : Application() {

    override fun onCreate() {
        super.onCreate()

        initLogging()
        initDi()
    }

    private fun initLogging() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
            Stetho.initializeWithDefaults(this)
        }
    }

    private fun initDi() {
        val appScope = AppScope(this)
        Injector.openScope(appScope)
    }
}