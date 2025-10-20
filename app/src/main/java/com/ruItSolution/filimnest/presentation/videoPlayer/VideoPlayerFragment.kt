package com.ruItSolution.filimnest.presentation.videoPlayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.media3.common.util.UnstableApi
import androidx.navigation.fragment.navArgs
import com.google.android.gms.ads.AdError
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.FullScreenContentCallback
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.MobileAds
import com.google.android.gms.ads.interstitial.InterstitialAd
import com.google.android.gms.ads.interstitial.InterstitialAdLoadCallback
import com.ruItSolution.filimnest.R
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

    // Use the Test Ad Unit ID for development
    private val AD_UNIT_ID = "ca-app-pub-3940256099942544/1033173712"
    //  ca-app-pub-5314757729080874/7268601645

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        // Initialize Mobile Ads SDK
        MobileAds.initialize(requireContext()) {}

        // Start loading the interstitial ad
        loadInterstitialAd()
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
                    // Ad failed, so just show the webview
                    setupAndShowWebView()
                }

                override fun onAdLoaded(interstitialAd: InterstitialAd) {
                    Log.d(TAG, "Ad was loaded.")
                    mInterstitialAd = interstitialAd
                    // Hide progress bar, ad is ready to be shown
                    loading.dismiss()
                    setupAdCallbacks()
                    mInterstitialAd?.show(requireActivity())
                }
            })
    }

    private fun setupAdCallbacks() {
        mInterstitialAd?.fullScreenContentCallback = object : FullScreenContentCallback() {
            override fun onAdDismissedFullScreenContent() {
                // Called when ad is dismissed.
                Log.d(TAG, "Ad was dismissed.")
                mInterstitialAd = null
                // Show the webview now that the ad is done
                setupAndShowWebView()
            }

            override fun onAdFailedToShowFullScreenContent(adError: AdError) {
                // Called when ad fails to show.
                Log.d(TAG, "Ad failed to show: ${adError.message}")
                mInterstitialAd = null
                // Show the webview anyway
                setupAndShowWebView()
            }

            override fun onAdShowedFullScreenContent() {
                // Called when ad is shown.
                Log.d(TAG, "Ad showed fullscreen content.")
            }
        }
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun setupAndShowWebView() {
        // rotate the WebView to landscape
        requireActivity().requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE


        // Ensure this runs on the main thread, especially after ad callbacks
        activity?.runOnUiThread {
            loading.dismiss()
            binding.webView.visibility = View.VISIBLE // Make WebView visible

            val url = args.videoLink
            binding.webView.apply {
                loadUrl(url)
                settings.javaScriptEnabled = true
                webViewClient = WebViewClient()
                settings.setSupportZoom(true)
                // Optional: Improve video playback experience
                settings.mediaPlaybackRequiresUserGesture = false
            }
        }
    }

    override fun setAllClickListener() {}
    override fun allObserver() {}

    override fun onDestroy() {
        requireActivity().requestedOrientation = android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED
        super.onDestroy()
    }
}