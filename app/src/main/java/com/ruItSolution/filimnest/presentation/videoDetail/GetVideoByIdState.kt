package com.ruItSolution.filimnest.presentation.videoDetail

import com.ruItSolution.filimnest.domain.model.VideoModel

data class GetVideoByIdState(
    val loading: Boolean = false,
    val success: VideoModel? = null,
    val error: String? = null,
)
