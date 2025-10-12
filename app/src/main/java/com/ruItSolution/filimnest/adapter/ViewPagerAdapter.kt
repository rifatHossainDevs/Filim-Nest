package com.ruItSolution.filimnest.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ruItSolution.filimnest.AnimationFragment
import com.ruItSolution.filimnest.HomeFragment
import com.ruItSolution.filimnest.MovieFragment
import com.ruItSolution.filimnest.TvSeriesFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity) :
    FragmentStateAdapter(fragmentActivity) {

    private val fragments = listOf(
        HomeFragment(),
        MovieFragment(),
        TvSeriesFragment(),
        AnimationFragment()
    )

    override fun createFragment(position: Int): Fragment = fragments[position]
    override fun getItemCount(): Int = fragments.size

}