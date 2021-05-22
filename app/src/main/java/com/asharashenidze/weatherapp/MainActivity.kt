package com.asharashenidze.weatherapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import androidx.viewpager2.widget.ViewPager2
import com.asharashenidze.weatherapp.adapters.ViewPagerAdapter
import com.asharashenidze.weatherapp.fragments.HourFragment
import com.asharashenidze.weatherapp.fragments.TodayFragment

class MainActivity : AppCompatActivity() {

    private lateinit var firstButton: ImageView
    private lateinit var secondButton: ImageView
    private lateinit var viewpager: ViewPager2

    val firstFragment = TodayFragment()
    val secondFragment = HourFragment()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initFragments()
        initButtons()

    }

    private fun initFragments() {
        viewpager = findViewById(R.id.view_pager)
        viewpager.adapter = ViewPagerAdapter(arrayListOf(firstFragment, secondFragment),this)
        setFragment(0, viewpager)
    }

    private fun initButtons() {
        firstButton =  findViewById<ImageView>(R.id.btn_today)
        secondButton = findViewById<ImageView>(R.id.btn_hour)

        firstButton.setOnClickListener {
            setFragment(0, viewpager)
        }

        secondButton.setOnClickListener {
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