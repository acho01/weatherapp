package com.asharashenidze.weatherapp.fragmentsHouorlyWeatherResponse

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.weatherapp.R
import com.asharashenidze.weatherapp.City
import com.asharashenidze.weatherapp.service.HourlyWeatherResponse
import com.asharashenidze.weatherapp.service.WeatherServiceClient
import ge.ezarkua.listsdemo.HourWeatherDataAdapter


class HourFragment(var cityList: List<City>, var weatherService: WeatherServiceClient) : Fragment(R.layout.hourly_view_fragment), WeatherServiceClient.OnHourlyResponseListener {

    private lateinit var rcWeather: RecyclerView;

    private lateinit var view2: View;

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view2 = view
        rcWeather = view.findViewById<RecyclerView>(R.id.rv_items)
        weatherService.getHourlyWeather("Tbilisi", this)
        view.findViewById<TextView>(R.id.text_city).text = "Tbilisi"
    }

    override fun onHourlyWeatherResponse(hourlyWeatherResponse: HourlyWeatherResponse) {
        val adapter = HourWeatherDataAdapter(hourlyWeatherResponse.list)
        if (context != null) {
            view2.findViewById<TextView>(R.id.text_city).text = hourlyWeatherResponse.city.name
            val itemDecorator = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
            itemDecorator.setDrawable(context?.let { ContextCompat.getDrawable(it, R.drawable.divider) }!!)
            rcWeather.addItemDecoration(itemDecorator)
            rcWeather.adapter = adapter
        }
    }
}