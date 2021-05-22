package com.asharashenidze.weatherapp.service

import com.asharashenidze.weatherapp.DailyWeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("/data/2.5/weather")
    fun getDailyWeather(@Query("q") cityName: String,
                        @Query("appid") apiKey: String,
                        @Query("units")unit: String ) : Call<DailyWeatherResponse>

    @GET("/data/2.5/forecast")
    fun getHourWeather(@Query("q") cityName: String,
                        @Query("appid") apiKey: String,
                        @Query("units")unit: String ) : Call<HourlyWeatherResponse>
}