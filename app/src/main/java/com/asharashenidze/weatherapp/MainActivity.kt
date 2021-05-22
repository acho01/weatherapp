package com.asharashenidze.weatherapp

import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.asharashenidze.weatherapp.adapters.ViewPagerAdapter
import com.asharashenidze.weatherapp.fragments.TodayFragment
import com.asharashenidze.weatherapp.fragmentsHouorlyWeatherResponse.HourFragment
import com.asharashenidze.weatherapp.service.WeatherServiceClient
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var todayButton: ImageView
    private lateinit var hourButton: ImageView

    private lateinit var geoButton: ImageView
    private lateinit var ukButton: ImageView
    private lateinit var jamButton: ImageView

    private lateinit var viewpager: ViewPager2

    private val firstFragment = TodayFragment()
    private lateinit var secondFragment: HourFragment

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
        weatherService.getDailyWeather("Tbilisi", firstFragment)

    }

    private fun initFragments() {
        secondFragment = HourFragment(cityList, weatherService)
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
                weatherService.getDailyWeather(city.name, firstFragment)
                weatherService.getHourlyWeather(city.name, secondFragment)
            }
        }
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