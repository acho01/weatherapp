package com.asharashenidze.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    val firstFragment = TodayFragment()
    val secondFragment = HourFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setFragment(firstFragment)
        initButtons()

    }

    private fun initButtons() {
        val firstButton =  findViewById<ImageView>(R.id.btn_today)
        val secondButton = findViewById<ImageView>(R.id.btn_hour)

        firstButton.setOnClickListener {
            setFragment(firstFragment)
        }

        secondButton.setOnClickListener {
            setFragment(secondFragment)
        }
    }

    private fun setFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_layout, fragment)
            commit()
        }
    }
}