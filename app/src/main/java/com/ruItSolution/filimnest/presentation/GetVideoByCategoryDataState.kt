package com.ruItSolution.filimnest.presentation

import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO

data class GetVideoByCategoryDataState(
    val loading: Boolean = false,
    val success: ResponseGetVideoByCategoryPaginatedDTO? = null,
    val error: String? = null
)
