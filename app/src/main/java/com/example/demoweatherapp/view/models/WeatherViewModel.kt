package com.example.demoweatherapp.view.models


import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.demoweatherapp.models.History
import com.example.demoweatherapp.models.netwrok.weather.Weather
import com.shadow.voicepublishing.network.ResultWrapper
import com.example.demoweatherapp.repositories.ApiDataRepository

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch

import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val dataRepository: ApiDataRepository) :
    ViewModel() {

    val snakBarMessage = MutableLiveData<String>()
    val progressBar = MutableLiveData<Boolean>()
    val historyWeather= MutableLiveData<History>()
    val weatherResponse: LiveData<Weather>
        get() = _weatherResponse
   private val _weatherResponse= MutableLiveData<Weather>()

    fun getWeather(city: String) {
        showProgressBar(true)
        viewModelScope.launch {
            showProgressBar(true)
            dataRepository.getWeather(city)
                .let { response ->
                    showProgressBar(false)

                    when (response) {
                        is ResultWrapper.Success -> {
                            _weatherResponse.value = response.value
                            addHistory(city.toUpperCase(),System.currentTimeMillis())
                        }

                        else -> handleErrorType(response)
                    }
                }
        }
    }

    private fun showSnackBarMessage(message: String) {
        snakBarMessage.value = message
    }


    private fun showProgressBar(show: Boolean) {
        progressBar.value = show
    }

    fun addHistory(cityName:String,time:Long){

        historyWeather.value =History(cityName,time)

    }

    fun handleErrorType(error: ResultWrapper<Any>) {
        showProgressBar(false)
        when (error) {
            is ResultWrapper.NetworkError ->
                showSnackBarMessage("Internet not available")

            is ResultWrapper.GenericError ->
                showSnackBarMessage("" + error.error?.message)

            else -> {

            }
        }
    }
}