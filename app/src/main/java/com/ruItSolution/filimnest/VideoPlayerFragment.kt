package com.ruItSolution.filimnest

import android.widget.Toast
import androidx.media3.common.MediaItem
import androidx.media3.common.PlaybackException
import androidx.media3.common.Player
import androidx.media3.exoplayer.ExoPlayer
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentVideoPlayerBinding

class VideoPlayerFragment :
    BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {

    private lateinit var player: ExoPlayer

    override fun setAllClickListener() {
        setUpPlayer()

    }

    override fun allObserver() {

    }

    private fun setUpPlayer() {
        player = ExoPlayer.Builder(requireContext()).build()
        binding.playerView.player = player

        val mediaItem = MediaItem.fromUri("https://www.youtube.com/watch?v=0dun8xS-Pqg")
        player.setMediaItem(mediaItem)
        player.prepare()
        player.play()

        player.addListener(object: Player.Listener{
            override fun onPlayerError(error: PlaybackException) {
                Toast.makeText(requireContext(), "Error Playing Media", Toast.LENGTH_SHORT).show()
                super.onPlayerError(error)
            }
        })


    }

    override fun onStart() {
        super.onStart()
        player.playWhenReady = true
    }

    override fun onStop() {
        super.onStop()
        player.playWhenReady = false
    }

    override fun onDestroy() {
        super.onDestroy()
        player.release()

    }

}