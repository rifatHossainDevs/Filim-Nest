package com.ruItSolution.filimnest.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruItSolution.filimnest.databinding.ItemCategorySearchBinding
import com.ruItSolution.filimnest.domain.model.SearchCategoryItem
import dagger.hilt.android.AndroidEntryPoint

class SearchAdapter(
    val searchItem: MutableList<SearchCategoryItem>,
    private val onItemClick: (categoryId: Int) -> Unit
) :
    RecyclerView.Adapter<SearchAdapter.ViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        return ViewHolder(
            ItemCategorySearchBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = searchItem[position]
        holder.binding.tvName.text = item.name

        // Correct usage of itemView inside ViewHolder
        holder.itemView.setOnClickListener {
            onItemClick(item.categoryId) // <- pass categoryId
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateData(newList: List<SearchCategoryItem>) {
        searchItem.clear()
        searchItem.addAll(newList)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = searchItem.size

    class ViewHolder(val binding: ItemCategorySearchBinding) :
        RecyclerView.ViewHolder(binding.root)
}