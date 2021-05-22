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

    fun getDailyWeather(cityName: String, callback: OnWeatherResponseListener) {
        val retrofit: Retrofit = Retrofit.Builder()
                .baseUrl(serviceUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        var fakeApi = retrofit.create(WeatherService::class.java)

        var postsCall = fakeApi.getDailyWeather(cityName, apiKey, "metric")

        postsCall.enqueue(object : Callback<DailyWeatherResponse> {
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

    interface OnWeatherResponseListener {
        fun onDailyWeatherResponse(dailyWeatherResponse: DailyWeatherResponse)
    }
}