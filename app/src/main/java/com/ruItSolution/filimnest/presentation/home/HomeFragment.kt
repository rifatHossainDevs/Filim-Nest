package com.ruItSolution.filimnest.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.models.SlideModel
import com.ruItSolution.filimnest.R
import com.ruItSolution.filimnest.adapter.VideoListAdapter
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentHomeBinding
import com.ruItSolution.filimnest.presentation.GetVideoByCategoryViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    VideoListAdapter.SetOnItemClickListener {

    private lateinit var videoListAdapter: VideoListAdapter
    private val getVideoByCategoryViewModel: GetVideoByCategoryViewModel by viewModels()


    override fun setAllClickListener() {
        setImageSlider()

        getVideoByCategoryViewModel.getHollywoodVideosByCategory(
            categoryId = 2,
            page = 1,
            limit = 10
        )

        getVideoByCategoryViewModel.getBollywoodVideosByCategory(
            categoryId = 3,
            page = 1,
            limit = 10
        )

        getVideoByCategoryViewModel.getHorrorVideosByCategory(
            categoryId = 4,
            page = 1,
            limit = 10
        )

        getVideoByCategoryViewModel.getTamilVideosByCategory(
            categoryId = 5,
            page = 1,
            limit = 10
        )

        getVideoByCategoryViewModel.getAnimationVideosByCategory(
            categoryId = 6,
            page = 1,
            limit = 10
        )


    }


    override fun allObserver() {

        // Hollywood
        lifecycleScope.launch {
            getVideoByCategoryViewModel.getHollywoodVideoState.collect {response->
                if (response.loading){
                    loading.show()
                }
                if (response.error != null){
                    loading.dismiss()
                }
                if (response.success != null){
                    loading.dismiss()
                    // response.success.data?.docs to list of video model
                    val videoList = response.success.data!!.docs!!
                    videoListAdapter = VideoListAdapter(videoList, this@HomeFragment)
                    binding.rvHollywood.adapter = videoListAdapter
                }
            }
        }


        // Bollywood
        lifecycleScope.launch {
            getVideoByCategoryViewModel.getBollywoodVideoState.collect {response->
                if (response.loading){
                    loading.show()
                }
                if (response.error != null){
                    loading.dismiss()
                }
                if (response.success != null){
                    loading.dismiss()
                    // response.success.data?.docs to list of video model
                    val videoList = response.success.data!!.docs!!
                    videoListAdapter = VideoListAdapter(videoList, this@HomeFragment)
                    binding.rvBollywood.adapter = videoListAdapter
                }
            }
        }


        lifecycleScope.launch {
            getVideoByCategoryViewModel.getHorrorVideoState.collect {response->
                if (response.loading){
                    loading.show()
                }
                if (response.error != null){
                    loading.dismiss()
                }
                if (response.success != null){
                    loading.dismiss()
                    // response.success.data?.docs to list of video model
                    val videoList = response.success.data!!.docs!!
                    videoListAdapter = VideoListAdapter(videoList, this@HomeFragment)
                    binding.rvHorror.adapter = videoListAdapter
                }
            }
        }


        lifecycleScope.launch {
            getVideoByCategoryViewModel.getTamilVideoState.collect {response->
                if (response.loading){
                    loading.show()
                }
                if (response.error != null){
                    loading.dismiss()
                }
                if (response.success != null){
                    loading.dismiss()
                    // response.success.data?.docs to list of video model
                    val videoList = response.success.data!!.docs!!
                    videoListAdapter = VideoListAdapter(videoList, this@HomeFragment)
                    binding.rvTamil.adapter = videoListAdapter
                }
            }
        }


        lifecycleScope.launch {
            getVideoByCategoryViewModel.getAnimationVideoState.collect { response->
                if (response.loading){
                    loading.show()
                }
                if (response.error != null){
                    loading.dismiss()
                }
                if (response.success != null){
                    loading.dismiss()
                    // response.success.data?.docs to list of video model
                    val videoList = response.success.data!!.docs!!
                    videoListAdapter = VideoListAdapter(videoList, this@HomeFragment)
                    binding.rvAnimation.adapter = videoListAdapter
                }
            }
        }
    }

    private fun setImageSlider() {
        val images = listOf(
            R.drawable.img_1,
            R.drawable.img_2,
            R.drawable.img_5,
            R.drawable.img_4,
        )

        val slideImageList = mutableListOf(
            SlideModel(images[0]),
            SlideModel(images[1]),
            SlideModel(images[2]),
            SlideModel(images[3]),
        )

        val imageSlider = binding.imageSlider
        imageSlider.setImageList(slideImageList)
    }

    override fun onItemClick(videoID: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVideoDetailFragment(videoID))
    }

}