package com.ruItSolution.filimnest.presentation.videoDetail

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentVideoDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class VideoDetailFragment : BaseFragment<FragmentVideoDetailBinding>(FragmentVideoDetailBinding::inflate) {
    private val getVideoByIdViewModel: GetVideoByIdViewModel by viewModels()

    private val arg: VideoDetailFragmentArgs by navArgs()

    override fun setAllClickListener() {
        getVideoByIdViewModel.getVideoById(arg.videoId)
    }

    override fun allObserver() {
        lifecycleScope.launch {
            getVideoByIdViewModel.getVideoByIdState.collect { response ->
                if (response.loading){
                    loading.show()
                }
                if (response.success != null){
                    loading.dismiss()
                    binding.tvMovieName.text = response.success.title
                    binding.tvMovieDescription.text = response.success.description

                    val secureUrl = response.success.thumbnail.replace("http://", "https://")

                    Glide.with(requireContext())
                        .load(secureUrl)
                        .into(binding.ivMovieImage)
                }
                if (response.error != null){
                    loading.dismiss()
                }
            }
        }
    }
}