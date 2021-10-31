package com.yurich.mapsapp.presentation

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.yurich.mapsapp.presentation.main.MainFragment
import com.yurich.mapsapp.presentation.map.MapFragment

class ViewPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment =
        when (position) {
            0 -> MainFragment.newInstance()
            1 -> MapFragment.newInstance()
            else -> throw IllegalStateException("There should be 2 fragments in total")
        }

}