package ge.ezarkua.listsdemo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asharashenidze.weatherapp.R
import com.asharashenidze.weatherapp.service.IconProvider
import com.asharashenidze.weatherapp.service.WeatherData

class HourWeatherDataAdapter(var list: List<WeatherData>) : RecyclerView.Adapter<HourWeatherDataViewHolder>() {


    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: HourWeatherDataViewHolder, position: Int) {
        var item = list[position]
        holder.time.text = item.dt_txt
        holder.desc.text = item.weather[0].description
        holder.temp.text = (Math.round(item.main.temp).toString() + "\u00B0")
        IconProvider.setImageInto(item.weather.get(0).icon, holder.icon)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HourWeatherDataViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.single_hour_weather, parent, false)
        return HourWeatherDataViewHolder(view)
    }
}

class HourWeatherDataViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var time = itemView.findViewById<TextView>(R.id.weather_hour_timestamp)
    var temp = itemView.findViewById<TextView>(R.id.weather_hour_temp)
    var desc = itemView.findViewById<TextView>(R.id.weather_hour_desc)
    var icon = itemView.findViewById<ImageView>(R.id.wather_hour_icon)
}

