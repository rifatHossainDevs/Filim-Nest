package com.ruItSolution.filimnest.presentation.home

import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.denzcoskun.imageslider.ImageSlider
import com.denzcoskun.imageslider.interfaces.ItemClickListener
import com.denzcoskun.imageslider.models.SlideModel
import com.ruItSolution.filimnest.MainActivity
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
    private val getLatestVideoViewModel: LatestVideoViewModel by viewModels()


    override fun setAllClickListener() {
        //setImageSlider()

        getLatestVideoViewModel.getLatestVideo()

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


        // Get Latest Video ViewModel
        lifecycleScope.launch {
            getLatestVideoViewModel.latestVideoState.collect { response->
                if (response.loading){
                    loading.show()
                }
                if (response.error != null){
                    loading.dismiss()
                }
                if (response.success != null){
                    loading.dismiss()

                    val latestVideoList = response.success

                    if (latestVideoList.isNotEmpty()) {
                        // 1. Convert your video list to a List<SlideModel>
                        val slideImages = ArrayList<SlideModel>()
                        for (video in latestVideoList) {
                            // TODO: Replace 'video.thumbnail' and 'video.title'
                            // with the real properties from your Video model
                            slideImages.add(
                                SlideModel(video.thumbnail, video.title)
                            )
                        }

                        // 2. Set the images to the slider
                        binding.imageSlider.setImageList(slideImages)

                        // 3. (Optional) Set a click listener to navigate to details
                        binding.imageSlider.setItemClickListener(object : ItemClickListener {
                            override fun onItemSelected(position: Int) {
                                val videoId = latestVideoList[position].id
                                onItemClick(videoId)
                            }

                            override fun doubleClick(position: Int) {
                                // You can leave this empty
                            }
                        })
                    }
                }
            }
        }
    }

    override fun onItemClick(videoID: String) {
        findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToVideoDetailFragment(videoID))
    }

    override fun onResume() {
        super.onResume()
        (requireActivity() as MainActivity).showToolBarAndDrawer()
    }

}