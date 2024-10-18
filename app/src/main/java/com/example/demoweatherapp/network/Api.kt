package com.example.demoweatherapp.network

import com.example.demoweatherapp.models.netwrok.weather.Weather
import com.shadow.voicepublishing.utils.CONSTANTS.FORCAST
import retrofit2.http.GET
import retrofit2.http.Query


interface Api {


    @GET(FORCAST)
    suspend fun getWeather(
        @Query("key") apiKey: String,
        @Query("q") cityName: String,
        @Query("days") days: Int,
        @Query("aqi") aqi: String?,
        @Query("alerts") alerts: String
    ): Weather


}