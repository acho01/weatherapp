package com.asharashenidze.weatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.weatherapp.DailyWeatherResponse
import com.asharashenidze.weatherapp.R
import com.asharashenidze.weatherapp.service.IconProvider
import com.asharashenidze.weatherapp.service.WeatherServiceClient


class TodayFragment : Fragment(), WeatherServiceClient.OnDailyResponseListener {

    private lateinit var view2: View;

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.today_view_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view2 = view
    }

    override fun onDailyWeatherResponse(dailyWeatherResponse: DailyWeatherResponse) {
        val temperature = view2.findViewById<TextView>(R.id.text_temperature)
        val description = view2.findViewById<TextView>(R.id.text_weather_description)
        val city = view2.findViewById<TextView>(R.id.text_city)
        val icon = view2.findViewById<ImageView>(R.id.image_weather)
        val details_temperature = view2.findViewById<TextView>(R.id.text_temperature_value)
        val details_feels = view2.findViewById<TextView>(R.id.text_feelslike_value)
        val details_humidity = view2.findViewById<TextView>(R.id.text_humidity_value)
        val details_pressure = view2.findViewById<TextView>(R.id.text_pressure_value)

        temperature?.text = (Math.round(dailyWeatherResponse.main.temp).toString() + "\u00B0")
        description?.text = (dailyWeatherResponse.weather.get(0).description)
        city?.text = (dailyWeatherResponse.name)
        IconProvider.setImageInto(dailyWeatherResponse.weather.get(0).icon, icon)
        details_temperature?.text = (Math.round(dailyWeatherResponse.main.temp).toString() + "\u00B0")
        details_feels?.text = (Math.round(dailyWeatherResponse.main.feels_like).toString() + "\u00B0")
        details_humidity?.text = (dailyWeatherResponse.main.humidity.toString() + "%")
        details_pressure?.text = (dailyWeatherResponse.main.pressure.toString())
    }
}