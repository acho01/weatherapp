package com.asharashenidze.weatherapp

import android.widget.ImageView
import com.bumptech.glide.Glide

class IconProvider() {
    companion object {
        public fun setImageInto(imageName: String, imageView: ImageView) {
            val name = imageName + "@2x.png";
            val imageUrl: String = "https://openweathermap.org/img/wn/" + name;
            Glide.with(imageView.rootView)
                .load(imageUrl)
                .into(imageView)
        }
    }
}
