package com.example.demoweatherapp.repositories


import com.example.demoweatherapp.models.netwrok.weather.Weather
import com.example.demoweatherapp.network.Api
import com.shadow.voicepublishing.network.ResultWrapper
import com.shadow.voicepublishing.repositories.safeApiCall

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import retrofit2.Retrofit
import javax.inject.Inject

class ApiDataRepository @Inject constructor() {
    @Inject
    lateinit var retrofit: Retrofit
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO

    suspend fun getWeather(city: String): ResultWrapper<Weather> {
        return safeApiCall(dispatcher) {
            retrofit.create(Api::class.java).getWeather(
                apiKey = "83de4214a76f4464b00143404241710",
                cityName = city,
                days = 1,
                aqi = "no",
                alerts = "no"
            );
        }
    }

}