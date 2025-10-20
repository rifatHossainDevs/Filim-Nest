package com.ruItSolution.filimnest.presentation.categoryVideo

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.ruItSolution.filimnest.adapter.CategoryVideoListAdapter
import com.ruItSolution.filimnest.adapter.VideoListAdapter
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.core.utils.Constants
import com.ruItSolution.filimnest.databinding.FragmentVideoListByCategoryBinding
import com.ruItSolution.filimnest.presentation.GetVideoByCategoryViewModel
import com.ruItSolution.filimnest.presentation.home.HomeFragmentDirections
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import kotlin.getValue

@AndroidEntryPoint
class VideoListByCategoryFragment : BaseFragment<FragmentVideoListByCategoryBinding>(
    FragmentVideoListByCategoryBinding::inflate
), CategoryVideoListAdapter.SetOnItemClickListener {

    private val args: VideoListByCategoryFragmentArgs by navArgs()

    private val getVideoByCategoryViewModel: GetVideoByCategoryViewModel by viewModels()
    private lateinit var categoryVideoListAdapter: CategoryVideoListAdapter

    private var prevPage: Int? = null
    private var nextPage: Int? = null
    private var currentPage: Int? = 1


    override fun setAllClickListener() {
        val categoryNo = args.categoryNo
        val categoryName = args.categoryName

        getVideoByCategoryViewModel.getVideosByCategory(
            categoryId = categoryNo.toInt(),
            page = 1,
            limit = Constants.PAGINATION_LIMIT
        )

        binding.tvCategoryName.text = categoryName

        binding.apply {
            btnNextPage.setOnClickListener {
                if (nextPage != null) {
                    getVideoByCategoryViewModel.getVideosByCategory(
                        categoryId = categoryNo.toInt(),
                        page = nextPage!!,
                        limit = Constants.PAGINATION_LIMIT
                    )
                }
            }

            btnPreviousPage.setOnClickListener {
                if (prevPage != null) {
                    getVideoByCategoryViewModel.getVideosByCategory(
                        categoryId = categoryNo.toInt(),
                        page = prevPage!!,
                        limit = Constants.PAGINATION_LIMIT
                    )
                }
            }
        }
    }

    override fun allObserver() {
        lifecycleScope.launch {
            getVideoByCategoryViewModel.getVideoByCategoryState.collect { response ->
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
                    categoryVideoListAdapter = CategoryVideoListAdapter(videoList, this@VideoListByCategoryFragment)
                    binding.rvVideosByCategory.adapter = categoryVideoListAdapter



                    if (response.success.data.totalPages == 1) {
                        binding.llPaginatePage.visibility = View.GONE
                    }

                    if (response.success.data.hasPrevPage == false) {
                        binding.btnPreviousPage.isEnabled = false
                    } else {
                        binding.btnPreviousPage.isEnabled = true
                        prevPage = response.success.data.prevPage
                    }

                    if (response.success.data.hasNextPage == false) {
                        binding.btnNextPage.isEnabled = false
                    } else {
                        binding.btnNextPage.isEnabled = true
                        nextPage = response.success.data.nextPage!!
                    }

                }
            }
        }
    }

    override fun onItemClick(videoID: String) {
        findNavController().navigate(VideoListByCategoryFragmentDirections.actionVideoListByCategoryFragmentToVideoDetailFragment(videoID))
    }


}

