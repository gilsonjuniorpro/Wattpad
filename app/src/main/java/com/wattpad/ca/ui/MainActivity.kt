package com.wattpad.ca.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.google.android.material.tabs.TabLayoutMediator
import com.wattpad.ca.R
import com.wattpad.ca.adapters.WattpadPagerAdapter
import com.wattpad.ca.core.WattpadApplication
import com.wattpad.ca.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setSupportActionBar(toolBar)

        WattpadApplication.activity = this

        viewPager.adapter = WattpadPagerAdapter(this)
        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.setText(
                if (position == 0) {
                    R.string.tab_stories
                } else {
                    R.string.tab_favorites
                }
            )
        }.attach()
    }
}