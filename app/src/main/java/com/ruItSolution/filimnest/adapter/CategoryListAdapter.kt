package com.ruItSolution.filimnest.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ruItSolution.filimnest.databinding.ItemLayoutCategoryBinding
import com.ruItSolution.filimnest.domain.model.CategoryModel

class CategoryListAdapter(val categoryList: List<CategoryModel>,
    private val onItemClickListener: SetOnItemClickListener
    ): RecyclerView.Adapter<CategoryListAdapter.CategoryViewHolder>() {

    fun interface SetOnItemClickListener {
        fun onItemClickCategory(categoryNo: String,categoryName: String)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CategoryViewHolder {
        val binding =
            ItemLayoutCategoryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CategoryViewHolder(binding)
    }

    override fun onBindViewHolder(
        holder: CategoryViewHolder,
        position: Int
    ) {
        holder.apply {
            binding.apply {
                tvItemName.text = categoryList[position].categoryName
            }

            itemView.setOnClickListener {
                onItemClickListener.onItemClickCategory(
                    categoryList[position].categoryNo.toString(),
                    categoryList[position].categoryName.toString()
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return categoryList.size
    }


    inner class CategoryViewHolder(val binding: ItemLayoutCategoryBinding): RecyclerView.ViewHolder(binding.root)
}