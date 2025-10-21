package com.ruItSolution.filimnest.presentation.videoPlayer

import android.annotation.SuppressLint
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebViewClient
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.media3.common.util.UnstableApi
import androidx.navigation.fragment.navArgs
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.ruItSolution.filimnest.MainActivity
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentVideoPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@Suppress("DEPRECATION")
@UnstableApi
@AndroidEntryPoint
class VideoPlayerFragment :
    BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {

    private val args: VideoPlayerFragmentArgs by navArgs()
    private var mInterstitialAd: InterstitialAd? = null
    private val TAG = "VideoPlayerFragment"
    private val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712" // Test ad unit

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide everything (status bar, toolbar, drawer)
        (requireActivity() as MainActivity).hideToolBarAndDrawer()
        hideSystemUI()

        // Lock screen to landscape
        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE

        // Initialize ads and load
        MobileAds.initialize(requireContext()) {}
        loadInterstitialAd()
    }

    private fun hideSystemUI() {
        val window = requireActivity().window
        WindowInsetsControllerCompat(window, window.decorView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior =
                WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun loadInterstitialAd() {
        loading.show()
        val adRequest = AdRequest.Builder().build()

        InterstitialAd.load(
            requireContext(),
            AD_UNIT_ID,
            adRequest,
            object : InterstitialAdLoadCallback() {
                override fun onAdFailedToLoad(adError: LoadAdError) {
                    Log.d(TAG, "Ad failed to load: ${adError.message}")
                    mInterstitialAd = null
                    setupAndShowWebView()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    loading.dismiss()
                    setupAdCallbacks()
                    mInterstitialAd?.show(requireActivity())
                }
            })
    }

    private fun setupAdCallbacks() {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                mInterstitialAd = null
                setupAndShowWebView()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                mInterstitialAd = null
                setupAndShowWebView()
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupAndShowWebView() {
        activity?.runOnUiThread {
            loading.dismiss()
            binding.webView.visibility = View.VISIBLE

            val url = args.videoLink
            binding.webView.apply {
                loadUrl(url)
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.setSupportZoom(true)
                settings.mediaPlaybackRequiresUserGesture = false
            }
        }
    }

    private fun showSystemUI() {
        val window = requireActivity().window
        WindowInsetsControllerCompat(
            window,
            window.decorView
        ).show(WindowInsetsCompat.Type.systemBars())
    }

    override fun onDestroyView() {
        super.onDestroyView()

        showSystemUI()

        requireActivity().requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED

        (requireActivity() as MainActivity).showToolBarAndDrawer()

        binding.webView.apply {
            loadUrl("about:blank")
            stopLoading()
            clearHistory()
            removeAllViews()
            destroy()
        }
    }

    override fun setAllClickListener() {}
    override fun allObserver() {}
}
