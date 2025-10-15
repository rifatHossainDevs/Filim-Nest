package com.ruItSolution.filimnest.presentation.search

import com.ruItSolution.filimnest.domain.model.VideoModel

data class SearchVideoDataState(
    val loading: Boolean = false,
    val success: List<VideoModel>? = null,
    val error: String? = null
)
