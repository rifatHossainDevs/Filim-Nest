package com.ruItSolution.filimnest.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.ruItSolution.filimnest.domain.model.VideoModel

@Keep
data class ResponseGetVideoByIdDTO(
    @SerializedName("data")
    val `data`: Data? = null,
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

fun ResponseGetVideoByIdDTO.toDomain(): VideoModel {
    return VideoModel(
            categoryNo = this.data?.categoryNo,
            description = this.data?.description.orEmpty(),
            duration = this.data?.duration ?: 0,
            id = this.data?.id.orEmpty(),
            thumbnail = this.data?.thumbnail.orEmpty(),
            title = this.data?.title.orEmpty(),
            videoFile = this.data?.videoFile.orEmpty()
        )

}