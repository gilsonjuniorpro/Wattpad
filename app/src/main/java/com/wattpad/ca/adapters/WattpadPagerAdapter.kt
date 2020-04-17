package com.wattpad.ca.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wattpad.ca.fragments.WattpadFavoritesFragment
import com.wattpad.ca.fragments.WattpadListFragment

class WattpadPagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        return if (position == 0) WattpadListFragment() else WattpadFavoritesFragment()
    }
}