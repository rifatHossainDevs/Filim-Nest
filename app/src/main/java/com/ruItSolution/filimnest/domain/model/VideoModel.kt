package com.ruItSolution.filimnest.domain.model


data class VideoModel(
    val categoryNo: List<Int?>?,
    val description: String,
    val duration: Int,
    val id: String,
    val thumbnail: String,
    val title: String,
    val videoFile: String
)
