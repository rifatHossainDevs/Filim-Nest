package com.ruItSolution.filimnest

import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruItSolution.filimnest.adapter.SearchAdapter
import com.ruItSolution.filimnest.adapter.SearchVideoAdapter
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSearchBinding
import com.ruItSolution.filimnest.domain.model.SearchCategoryItem
import com.ruItSolution.filimnest.presentation.GetVideoByCategoryViewModel
import com.ruItSolution.filimnest.presentation.search.SearchVideoViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var categoryAdapter: SearchAdapter
    private lateinit var allCategories: List<SearchCategoryItem>
    private val getSearchVideoViewModel: SearchVideoViewModel by viewModels()
    private val getVideoByCategoryViewModel: GetVideoByCategoryViewModel by viewModels()

    override fun setAllClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun allObserver() {
        observeSearchVideos()
        observeVideoByCategory()
    }

    private fun observeSearchVideos() {
        lifecycleScope.launch {
            getSearchVideoViewModel.searchVideoState.collect { state ->
                when {
                    state.loading -> loading.show()
                    state.error != null -> loading.dismiss()
                    state.success != null -> {
                        loading.dismiss()

                        // Get non-null docs
                        val videoList = state.success ?: emptyList()

                        // Set up adapter
                        val adapter = SearchVideoAdapter(videoList) { videoId ->
                            val action =
                                SearchFragmentDirections.actionSearchFragmentToVideoDetailFragment(
                                    videoId
                                )
                            findNavController().navigate(action)
                        }

                        // Show results RecyclerView
                        binding.rvSearchMovieList.apply {
                            layoutManager = GridLayoutManager(requireContext(), 2)
                            this.adapter = adapter
                            visibility = if (videoList.isNotEmpty()) View.VISIBLE else View.GONE
                        }

                        // Hide category list
                        binding.recyclerView.visibility = View.GONE

                        // Show "No Match Found" if empty
                        binding.tvNoMatchFound.visibility =
                            if (videoList.isEmpty()) View.VISIBLE else View.GONE
                    }
                }
            }
        }
    }

    private fun observeVideoByCategory() {
        lifecycleScope.launch {
            getVideoByCategoryViewModel.getVideoByCategoryState.collect { state ->

                if (state.success != null) {
                    val docs = state.success.data?.docs?.filterNotNull() ?: emptyList()
                    val categoryId = docs.firstOrNull()?.categoryNo?.firstOrNull() ?: return@collect

                    // Navigate only once
                    val action =
                        SearchFragmentDirections.actionSearchFragmentToVideoListByCategoryFragment(
                            categoryNo = categoryId.toString(),
                            categoryName = ""
                        )
                    findNavController().navigate(action)

                    // Reset state after navigation to prevent repeated navigation
                    getVideoByCategoryViewModel.resetState()
                }

            }
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideToolBarAndDrawer()

        setupCategoryRecyclerView()
        setupSearchView()
    }

    private fun setupCategoryRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        allCategories = listOf(
            SearchCategoryItem("Latest", 1),
            SearchCategoryItem("Popular", 2),
            SearchCategoryItem("Top Rated", 3),
            SearchCategoryItem("Upcoming", 4),
            SearchCategoryItem("Action", 5),
            SearchCategoryItem("Romantic", 6),
            SearchCategoryItem("Horror", 7)
        )

        categoryAdapter = SearchAdapter(allCategories.toMutableList()) { categoryId ->
            // Call getVideosByCategory and navigate
            getVideoByCategoryViewModel.getVideosByCategory(categoryId, page = 1, limit = 20)
        }
        binding.recyclerView.adapter = categoryAdapter
    }

    private fun setupSearchView() {
        binding.searchView.isIconified = false

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText.isNullOrEmpty()) {
                    binding.recyclerView.visibility = View.VISIBLE
                    binding.rvSearchMovieList.visibility = View.GONE
                    binding.tvNoMatchFound.visibility = View.GONE
                } else {
                    // Trigger search API only if text changed
                    getSearchVideoViewModel.searchVideo(newText)
                }
                return true
            }
        })

        // Show keyboard safely
        binding.searchView.post {
            val focusedView = binding.searchView.findFocus()
            if (focusedView?.windowToken != null) {
                val imm =
                    requireContext().getSystemService(InputMethodManager::class.java) as? InputMethodManager
                imm?.showSoftInput(focusedView, InputMethodManager.SHOW_IMPLICIT)
            }
        }
    }
}
