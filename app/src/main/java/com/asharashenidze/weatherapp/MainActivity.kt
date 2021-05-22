package com.asharashenidze.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.asharashenidze.weatherapp.adapters.ViewPagerAdapter
import com.asharashenidze.weatherapp.fragments.HourFragment
import com.asharashenidze.weatherapp.fragments.TodayFragment
import com.asharashenidze.weatherapp.service.IconProvider
import com.asharashenidze.weatherapp.service.WeatherServiceClient

class MainActivity : AppCompatActivity(), WeatherServiceClient.OnWeatherResponseListener {

    private lateinit var todayButton: ImageView
    private lateinit var hourButton: ImageView

    private lateinit var geoButton: ImageView
    private lateinit var ukButton: ImageView
    private lateinit var jamButton: ImageView

    private lateinit var viewpager: ViewPager2

    private val firstFragment = TodayFragment()
    private val secondFragment = HourFragment()

    private val weatherService = WeatherServiceClient()

    private val cityList = arrayListOf<City>(
            City("Tbilisi", R.id.btn_geo),
            City("London", R.id.btn_uk),
            City("Kingston", R.id.btn_jam))


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
        initButtons()
        weatherService.getDailyWeather("Tbilisi", this)

    }

    private fun initFragments() {
        viewpager = findViewById(R.id.view_pager)
        viewpager.adapter = ViewPagerAdapter(arrayListOf(firstFragment, secondFragment),this)
        setFragment(0, viewpager)
    }

    private fun initButtons() {
        initNavigationButtons()
        initCountryButtons()
    }

    private fun initCountryButtons() {
        geoButton = findViewById<ImageView>(R.id.btn_geo)
        ukButton = findViewById<ImageView>(R.id.btn_uk)
        jamButton = findViewById<ImageView>(R.id.btn_jam)

        initCityListeners()

    }

    private fun initCityListeners() {
        for (city in cityList) {
            val cityButton = findViewById<ImageView>(city.buttonId)
            cityButton.setOnClickListener {
                weatherService.getDailyWeather(city.name, this)
            }
        }
    }

    override fun onDailyWeatherResponse(dailyWeatherResponse: DailyWeatherResponse) {
        val temperature = findViewById<TextView>(R.id.text_temperature)
        val description = findViewById<TextView>(R.id.text_weather_description)
        val city = findViewById<TextView>(R.id.text_city)
        val icon = findViewById<ImageView>(R.id.image_weather)
        val details_temperature = findViewById<TextView>(R.id.text_temperature_value)
        val details_feels = findViewById<TextView>(R.id.text_feelslike_value)
        val details_humidity = findViewById<TextView>(R.id.text_humidity_value)
        val details_pressure = findViewById<TextView>(R.id.text_pressure_value)

        temperature?.text = (Math.round(dailyWeatherResponse.main.temp).toString() + "\u00B0")
        description?.text = (dailyWeatherResponse.weather.get(0).description)
        city?.text = (dailyWeatherResponse.name)
        IconProvider.setImageInto(dailyWeatherResponse.weather.get(0).icon, icon)
        details_temperature?.text = (Math.round(dailyWeatherResponse.main.temp).toString() + "\u00B0")
        details_feels?.text = (Math.round(dailyWeatherResponse.main.feels_like).toString() + "\u00B0")
        details_humidity?.text = (dailyWeatherResponse.main.humidity.toString() + "%")
        details_pressure?.text = (dailyWeatherResponse.main.pressure.toString())
    }

    private fun initNavigationButtons() {
        todayButton =  findViewById<ImageView>(R.id.btn_today)
        hourButton = findViewById<ImageView>(R.id.btn_hour)

        todayButton.setOnClickListener {
            setFragment(0, viewpager)
        }

        hourButton.setOnClickListener {
            setFragment(1, viewpager)
        }
    }

    private fun setFragment(fragmentIndex: Int, viewPager: ViewPager2) {
        supportFragmentManager.beginTransaction().apply {
            viewPager.setCurrentItem(fragmentIndex, true)
            commit()
        }
    }
}