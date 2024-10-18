package com.example.demoweatherapp

import android.app.Application
import dagger.hilt.android.HiltAndroidApp


@HiltAndroidApp
class DemoWeather : Application() {

    override fun onCreate() {
        super.onCreate()

    }
}