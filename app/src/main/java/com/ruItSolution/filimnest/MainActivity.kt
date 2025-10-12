package com.ruItSolution.filimnest

import android.graphics.Typeface
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.google.android.material.tabs.TabLayoutMediator
import com.ruItSolution.filimnest.adapter.ViewPagerAdapter
import com.ruItSolution.filimnest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val tabTitles = listOf("Home", "Movies", "TV Series", "Animation")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViewPager()
        setupTabs()
        setInitialTabColor()
        attachTabSelectedListener()

    }
    private fun setupViewPager() {
        val adapter = ViewPagerAdapter(this)
        binding.viewPager.adapter = adapter
    }
    private fun setupTabs() {
        TabLayoutMediator(binding.tabLayout, binding.viewPager) { tab, position ->
            val textView = TextView(this).apply {
                text = tabTitles[position]
                //setTextSize(TypedValue.COMPLEX_UNIT_SP, 16f)
                setTypeface(null, Typeface.BOLD)
                gravity = Gravity.CENTER
                setTextColor(ContextCompat.getColor(this@MainActivity, R.color.tab_unselected))
            }
            tab.customView = textView
        }.attach()
    }

    private fun setInitialTabColor() {
        binding.tabLayout.post {
            val firstTab = binding.tabLayout.getTabAt(binding.tabLayout.selectedTabPosition)
            val textView = firstTab?.customView as? TextView
            textView?.setTextColor(ContextCompat.getColor(this, android.R.color.white))
        }
    }

    private fun attachTabSelectedListener() {
        binding.tabLayout.addOnTabSelectedListener(object :
            com.google.android.material.tabs.TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                val textView = tab?.customView as? TextView
                textView?.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        android.R.color.white
                    )
                )
            }

            override fun onTabUnselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {
                val textView = tab?.customView as? TextView
                textView?.setTextColor(
                    ContextCompat.getColor(
                        this@MainActivity,
                        R.color.tab_unselected
                    )
                )
            }

            override fun onTabReselected(tab: com.google.android.material.tabs.TabLayout.Tab?) {}
        })
    }
}