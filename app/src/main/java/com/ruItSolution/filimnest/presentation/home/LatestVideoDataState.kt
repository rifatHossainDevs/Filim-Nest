package com.ruItSolution.filimnest.presentation.home

import com.ruItSolution.filimnest.domain.model.VideoModel

data class LatestVideoDataState(
    val loading: Boolean = false,
    val success: List<VideoModel>? = null,
    val error: String? = null
)
