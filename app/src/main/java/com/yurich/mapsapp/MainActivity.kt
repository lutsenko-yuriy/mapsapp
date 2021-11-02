package com.yurich.mapsapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.yurich.mapsapp.presentation.ViewPagerAdapter
import com.yurich.mapsapp.presentation.main.MainFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), MainFragment.OnVehicleSelectedListener {

    private lateinit var tabs: TabLayout
    private lateinit var pager: ViewPager2

    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        tabs = findViewById(R.id.tab_layout)
        pager = findViewById(R.id.view_pager)

        pager.isUserInputEnabled = false

        viewPagerAdapter = ViewPagerAdapter(this)

        pager.adapter = viewPagerAdapter

        TabLayoutMediator(tabs, pager) { tab, position ->
            tab.setText(
                when (position) {
                    0 -> R.string.tab_list
                    1 -> R.string.tab_map
                    else -> throw IllegalStateException("There should be 2 fragments in total")
                }
            )
        }.attach()
    }

    override fun onVehicleSelected() {
        pager.setCurrentItem(1, true)
    }
}