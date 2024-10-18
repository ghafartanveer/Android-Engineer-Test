package com.example.demoweatherapp

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.demoweatherapp.adapters.HistoryAdapter
import com.example.demoweatherapp.databinding.ActivityMainBinding
import com.example.demoweatherapp.interfaces.OnLocationSelectedListener
import com.example.demoweatherapp.models.netwrok.weather.Weather
import com.example.demoweatherapp.ui.bottomSheet.LocationBottomSheet
import com.example.demoweatherapp.ui.dialogs.LoadingDialog
import com.example.demoweatherapp.utils.timeEpochToFormatTime
import com.example.demoweatherapp.view.models.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), OnLocationSelectedListener {

    lateinit var binding: ActivityMainBinding
    private val weatherViewModel: WeatherViewModel by viewModels()
    private val loaderDialog by lazy { LoadingDialog(this) }
    private lateinit var adapter: HistoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        setUpAdapter()
        btnListeners()

        viewModelListeners()


    }

    private fun setUpAdapter() {
        adapter = HistoryAdapter()
        binding.rvItemRecentSearches.adapter = adapter
        adapter.setOriginalList(arrayListOf())

    }

    private fun viewModelListeners() {

        with(weatherViewModel) {
            getWeather("London")
            progressBar.observe(this@MainActivity) {
                if (it) loaderDialog.show()
                else loaderDialog.dismiss()
            }
            weatherResponse.observe(this@MainActivity) {
                it?.let { weather ->
                    setUI(weather)

                }
            }
            historyWeather.observe(this@MainActivity) {
                adapter.addItem(it)
            }
        }


    }

    private fun setUI(weather: Weather) {
        binding.tvTemp.text = weather.current.temp_c.toString()
        weather.forecast.forecastday[0].hour.forEachIndexed { index, hour ->
            if (index == 3) {
                return
            }
            if (index == 0) {
                binding.tvTempOne.text = "${hour.temp_c}C"
                binding.tvTempOneTime.text = timeEpochToFormatTime(hour.time_epoch.toLong())
            } else if (index == 1) {
                binding.tvTempTwo.text = "${hour.temp_c}C"
                binding.tvTempTwoTime.text = timeEpochToFormatTime(hour.time_epoch.toLong())

            } else if (index == 2) {
                binding.tvTempThree.text = "${hour.temp_c}C"
                binding.tvTempThreeTime.text = timeEpochToFormatTime(hour.time_epoch.toLong())

            }
        }
    }

    private fun btnListeners() {

        binding.btnSearch.setOnClickListener {
            val bottomSheet = LocationBottomSheet(this)
            // Set listener to this activity
            bottomSheet.show(supportFragmentManager, bottomSheet.tag)
        }

        binding.btnClearSearch.setOnClickListener {
            adapter.setOriginalList(arrayListOf())
        }

    }

    override fun onLocationSelected(location: String) {

        weatherViewModel.getWeather(location)
    }
}