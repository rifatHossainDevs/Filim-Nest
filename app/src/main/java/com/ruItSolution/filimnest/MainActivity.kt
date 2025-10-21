package com.ruItSolution.filimnest

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.navigation.findNavController
import com.ruItSolution.filimnest.adapter.DrawerItemAdapter
import com.ruItSolution.filimnest.databinding.ActivityMainBinding
import com.ruItSolution.filimnest.domain.model.DrawerItems
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var drawerAdapter: DrawerItemAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.toolbar.findViewById<ImageView>(R.id.searchIcon).setOnClickListener {
            findNavController(R.id.fragmentContainerView).navigate(R.id.searchFragment)
        }

        setupToolbar()
        setupDrawer()
        setupSocialLinks()
    }

    private fun setupSocialLinks() {
        // Facebook
        binding.menuNavigationView.findViewById<ImageView>(R.id.iv_facebook).setOnClickListener {
            openAppOrWebsite(
                appUrl = "https://www.facebook.com/maliha.hasan.9699", // open Facebook app if installed
                webUrl = "https://www.facebook.com/maliha.hasan.9699"
            )
        }

        // Telegram
        binding.menuNavigationView.findViewById<ImageView>(R.id.iv_telegram).setOnClickListener {
            openAppOrWebsite(
                appUrl = "https://www.facebook.com/maliha.hasan.9699",
                webUrl = "https://www.facebook.com/maliha.hasan.9699"
            )
        }

        // Website
        binding.menuNavigationView.findViewById<ImageView>(R.id.iv_website).setOnClickListener {
            openUrl("https://www.facebook.com/uthoai.marma.408294")
        }
    }

    private fun openAppOrWebsite(appUrl: String, webUrl: String) {
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(appUrl))
            startActivity(intent)
        } catch (e: Exception) {
            // If app not installed, open in browser
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(webUrl))
            startActivity(webIntent)
        }
    }

    private fun openUrl(url: String) {
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(intent)
    }

    fun hideToolBarAndDrawer() {
        binding.apply {
            toolbarCardView.visibility = View.GONE
            menuNavigationView.visibility = View.GONE
        }
    }

    fun showToolBarAndDrawer() {
        binding.apply {
            toolbarCardView.visibility = View.VISIBLE
            menuNavigationView.visibility = View.VISIBLE
        }
    }

    private fun setupToolbar() {
        // Menu icon click opens the drawer
        binding.ivMenuIcon.setOnClickListener {
            binding.main.openDrawer(GravityCompat.START)
        }
    }

    private fun setupDrawer() {
        val drawerItems = listOf(
            DrawerItems("Home", R.drawable.ic_home_white_24),
            DrawerItems("Prefer Movie", R.drawable.ic_favorite_movie_24),
            DrawerItems("About Us", R.drawable.ic_about_24),
            DrawerItems("Log Out", R.drawable.ic_logout_24)

        )

        drawerAdapter = DrawerItemAdapter(drawerItems) { item ->
            binding.main.closeDrawer(GravityCompat.START)
            handleDrawerItemClick(item)
        }

        binding.drawerItemRecyclerView.adapter = drawerAdapter
    }

    private fun handleDrawerItemClick(item: DrawerItems) {
        when (item.name) {
            "Home" -> {
                findNavController(R.id.fragmentContainerView).navigate(R.id.homeFragment)
                binding.main.closeDrawer(GravityCompat.START)
            }

            "Prefer Movie" -> {
                findNavController(R.id.fragmentContainerView).navigate(R.id.preferMovieFragment)
                binding.main.closeDrawer(GravityCompat.START)
            }

            "About Us" -> {
                findNavController(R.id.fragmentContainerView).navigate(R.id.aboutUsFragment)
                binding.main.closeDrawer(GravityCompat.START)
            }
            "Log Out"->{

            }

        }
    }

}