package com.asharashenidze.weatherapp.service

import com.asharashenidze.weatherapp.DailyWeatherResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherServiceClient () {

    private val apiKey = "4a680691e669097e5cf7a929bfc5c880"
    private val serviceUrl = "https://api.openweathermap.org"

    fun getDailyWeather(cityName: String, callback: OnDailyResponseListener) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(serviceUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var api = retrofit.create(WeatherService::class.java)

        var getCall = api.getDailyWeather(cityName, apiKey, "metric")

        getCall.enqueue(object : Callback<DailyWeatherResponse> {
            override fun onFailure(call: Call<DailyWeatherResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<DailyWeatherResponse>, response: Response<DailyWeatherResponse>) {
                if(response.isSuccessful){
                    response.body()?.let { callback.onDailyWeatherResponse(it) }
                }
            }

        })
    }

    fun getHourlyWeather(cityName: String, callback: OnHourlyResponseListener) {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(serviceUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        var api = retrofit.create(WeatherService::class.java)

        var getCall = api.getHourWeather(cityName, apiKey, "metric")

        getCall.enqueue(object : Callback<HourlyWeatherResponse> {
            override fun onFailure(call: Call<HourlyWeatherResponse>, t: Throwable) {
                TODO("Not yet implemented")
            }

            override fun onResponse(call: Call<HourlyWeatherResponse>, response: Response<HourlyWeatherResponse>) {
                if(response.isSuccessful){
                    response.body()?.let { callback.onHourlyWeatherResponse(it) }
                }
            }

        })
    }

    interface OnDailyResponseListener {
        fun onDailyWeatherResponse(dailyWeatherResponse: DailyWeatherResponse)
    }

    interface OnHourlyResponseListener {
        fun onHourlyWeatherResponse(hourlyWeatherResponse: HourlyWeatherResponse)
    }
}