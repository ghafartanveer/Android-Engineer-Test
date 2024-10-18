package com.example.demoweatherapp.models.netwrok.weather

data class Weather(
    val current: Current,
    val forecast: Forecast,
    val location: Location
)