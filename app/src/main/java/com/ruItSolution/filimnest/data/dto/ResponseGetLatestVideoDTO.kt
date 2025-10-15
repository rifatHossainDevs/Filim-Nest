package com.ruItSolution.filimnest.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.ruItSolution.filimnest.domain.model.VideoModel

@Keep
data class ResponseGetLatestVideoDTO(
    @SerializedName("data")
    val `data`: List<Data?>? = null,
    @SerializedName("message")
    val message: String? = null,
    @SerializedName("statusCode")
    val statusCode: Int? = null,
    @SerializedName("success")
    val success: Boolean? = null
){
    @Keep
    data class Data(
        @SerializedName("categoryNo")
        val categoryNo: List<Int?>? = null,
        @SerializedName("createdAt")
        val createdAt: String? = null,
        @SerializedName("description")
        val description: String? = null,
        @SerializedName("duration")
        val duration: Int? = null,
        @SerializedName("_id")
        val id: String? = null,
        @SerializedName("isPublished")
        val isPublished: Boolean? = null,
        @SerializedName("thumbnail")
        val thumbnail: String? = null,
        @SerializedName("title")
        val title: String? = null,
        @SerializedName("updatedAt")
        val updatedAt: String? = null,
        @SerializedName("__v")
        val v: Int? = null,
        @SerializedName("videoFile")
        val videoFile: String? = null,
        @SerializedName("views")
        val views: Int? = null
    )
}


fun ResponseGetLatestVideoDTO.toDomain(): List<VideoModel> {
    return this.data?.map {
        VideoModel(
            categoryNo = it?.categoryNo,
            description = it?.description.orEmpty(),
            duration = it?.duration ?: 0,
            id = it?.id.orEmpty(),
            thumbnail = it?.thumbnail.orEmpty(),
            title = it?.title.orEmpty(),
            videoFile = it?.videoFile.orEmpty()
        )
    } ?: emptyList()
}

