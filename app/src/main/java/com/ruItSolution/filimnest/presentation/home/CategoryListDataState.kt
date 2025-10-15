package com.ruItSolution.filimnest.presentation.home

import com.ruItSolution.filimnest.domain.model.CategoryModel

data class CategoryListDataState(
    val loading: Boolean = false,
    val success: List<CategoryModel>? = null,
    val error: String? = null
)
