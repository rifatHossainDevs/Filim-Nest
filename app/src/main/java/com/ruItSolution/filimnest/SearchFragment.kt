package com.ruItSolution.filimnest

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.appcompat.widget.SearchView
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.ruItSolution.filimnest.adapter.SearchAdapter
import com.ruItSolution.filimnest.core.base.BaseFragment
import com.ruItSolution.filimnest.databinding.FragmentSearchBinding
import com.ruItSolution.filimnest.domain.model.SearchCategoryItem

class SearchFragment : BaseFragment<FragmentSearchBinding>(FragmentSearchBinding::inflate) {

    private lateinit var adapter: SearchAdapter
    private lateinit var allItems: List<SearchCategoryItem>

    override fun setAllClickListener() {
        binding.ivBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }

    override fun allObserver() {}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (requireActivity() as MainActivity).hideToolBarAndDrawer()

        setupRecyclerView()
        setupSearchView()
    }

    private fun setupSearchView() {
        // Expand and focus automatically
        binding.searchView.isIconified = false
        binding.searchView.requestFocus()

        // Show soft keyboard after layout is ready
        binding.searchView.post {
            val imm = requireContext().getSystemService(InputMethodManager::class.java)
            imm?.showSoftInput(binding.searchView.findFocus(), InputMethodManager.SHOW_IMPLICIT)

        }



        // Handle query text change
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?) = false

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredList = if (newText.isNullOrEmpty()) {
                    allItems
                } else {
                    allItems.filter {
                        it.name.contains(newText, ignoreCase = true)
                    }
                }

                // Update RecyclerView
                adapter.updateData(filteredList.toMutableList())

                // Show "No Match Found" if filtered list is empty
                binding.apply {
                    recyclerView.visibility = if (filteredList.isEmpty()) View.GONE else View.VISIBLE
                    tvNoMatchFound.visibility = if (filteredList.isEmpty()) View.VISIBLE else View.GONE
                }

                return true
            }
        })
    }

    private fun setupRecyclerView() {
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())

        allItems = listOf(
            SearchCategoryItem("Latest"),
            SearchCategoryItem("Popular"),
            SearchCategoryItem("Top Rated"),
            SearchCategoryItem("Upcoming"),
            SearchCategoryItem("Action"),
            SearchCategoryItem("Romantic"),
            SearchCategoryItem("Horror")
        )

        adapter = SearchAdapter(allItems.toMutableList()) // Use mutable list
        binding.recyclerView.adapter = adapter
    }
}
