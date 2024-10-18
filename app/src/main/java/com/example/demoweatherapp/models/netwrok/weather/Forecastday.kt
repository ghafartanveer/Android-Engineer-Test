package com.example.demoweatherapp.models.netwrok.weather

data class Forecastday(
    val astro: Astro,
    val date: String,
    val date_epoch: Double,
    val day: Day,
    val hour: List<Hour>
)