package com.ruItSolution.filimnest

import android.graphics.Typeface
import android.view.Gravity
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.ruItSolution.filimnest.adapter.ViewPagerAdapter
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentContainerBinding

class ContainerFragment : BaseFragment<FragmentContainerBinding>(FragmentContainerBinding::inflate) {

    private val tabTitles = listOf("Home", "Movies", "TV Series", "Animation")

    override fun setAllClickListener() {
        setupViewPager()
        setupTabs()
        setInitialTabColor()
        attachTabSelectedListener()

        binding.viewPager.isUserInputEnabled = false
    }

    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(requireActivity())
        binding.viewPager.adapter = adapter
    }

    private fun setupTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val textView = TextView(requireContext()).apply {
                text = tabTitles[position]
                //setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                setTypeface(null, Typeface.BOLD)
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(requireContext(), R.color.tab_unselected))
            }
            tab.customView = textView
        }.attach()
    }

    private fun setInitialTabColor() {
        binding.tabLayout.post {
            val firstTab = binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition)
            val textView = firstTab?.customView as? TextView
            textView?.setTextColor(ContextCompat.getColor(requireContext(), android.R.color.white))
        }
    }

    private fun attachTabSelectedListener() {
        binding.tabLayout.addOnTabSelectedListener(object :
            com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                val textView = tab?.customView as? TextView
                textView?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        android.R.color.white
                    )
                )
            }

            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                val textView = tab?.customView as? TextView
                textView?.setTextColor(
                    ContextCompat.getColor(
                        requireContext(),
                        R.color.tab_unselected
                    )
                )
            }

            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }


    override fun allObserver() {

    }

}