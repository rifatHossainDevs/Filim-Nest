package com.ruItSolution.filimnest.presentation.videoPlayer

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.core.view.GestureDetectorCompat
import androidx.media3.common.util.UnstableApi
import androidx.media3.exoplayer.ExoPlayer
import androidx.navigation.fragment.navArgs
import com.ruItSolution.filimnest.R
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentVideoPlayerBinding
import dagger.hilt.android.AndroidEntryPoint


@Suppress("DEPRECATION")
@UnstableApi
@AndroidEntryPoint
class VideoPlayerFragment :
    BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {

    private var player: ExoPlayer? = null
    private lateinit var gestureLeft: GestureDetectorCompat
    private lateinit var gestureRight: GestureDetectorCompat

    private val args: VideoPlayerFragmentArgs by navArgs()

    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var url = args.videoLink
        binding.webView.apply {
            loadUrl(url)
            settings.javaScriptEnabled = true
            webViewClient = WebViewClient()
            settings.setSupportZoom(true)
        }
    }

    private fun initializePlayer() {
       /* player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        Log.d("TAG", "initializePlayer: ${args.videoLink}")

        val mediaItem = MediaItem.fromUri(
            args.videoLink
        )
        player?.setMediaItem(mediaItem)
        player?.prepare()
        player?.play()

        // Set controller timeout (unstable API)
        binding.playerView.controllerShowTimeoutMs = 4000*/
    }

    /*@SuppressLint("ClickableViewAccessibility")
    private fun setupDoubleTap() {
        val overlayLeft = binding.playerView.findViewById<View>(R.id.double_tap_left)
        val overlayRight = binding.playerView.findViewById<View>(R.id.double_tap_right)

        gestureLeft = GestureDetectorCompat(
            requireContext(),
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    player?.seekTo((player!!.currentPosition - 10_000).coerceAtLeast(0))
                    showOverlay(overlayLeft)
                    return true
                }
            })
        overlayLeft.setOnTouchListener { _, event -> gestureLeft.onTouchEvent(event); true }

        gestureRight = GestureDetectorCompat(
            requireContext(),
            object : GestureDetector.SimpleOnGestureListener() {
                override fun onDoubleTap(e: MotionEvent): Boolean {
                    val duration = player?.duration ?: 0
                    player?.seekTo((player!!.currentPosition + 10_000).coerceAtMost(duration))
                    showOverlay(overlayRight)
                    return true
                }
            })
        overlayRight.setOnTouchListener { _, event -> gestureRight.onTouchEvent(event); true }
    }

    private fun showOverlay(view: View) {
        view.visibility = View.VISIBLE
        val anim = AnimationUtils.loadAnimation(requireContext(), android.R.anim.fade_in)
        view.startAnimation(anim)
        view.postDelayed({ view.visibility = View.GONE }, 400)
    }

    private fun setupFullscreenButton() {
        binding.playerView.findViewById<View>(R.id.fullscreen_button).setOnClickListener {
            requireActivity().requestedOrientation =
                if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                else ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
        }
    }

    override fun onStart() {
        super.onStart()
        player?.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player?.pause()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        player?.release()
        player = null
    }*/

    override fun setAllClickListener() {}
    override fun allObserver() {}



    /*
    * @Deprecated("Deprecated in Java")
    fun onBackPressed() {
        if (binding.webView.canGoBack()){
            binding.webView.goBack()
        } else {
            super.onBackPressed()
        }
    }*/



}