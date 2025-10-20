package com.ruItSolution.filimnest.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep

@Keep
data class ResponseGetVideoByCategoryPaginatedDTO(
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
        @SerializedName("docs")
        val docs: List<Doc?>? = null,
        @SerializedName("hasNextPage")
        val hasNextPage: Boolean? = null,
        @SerializedName("hasPrevPage")
        val hasPrevPage: Boolean? = null,
        @SerializedName("limit")
        val limit: Int? = null,
        @SerializedName("nextPage")
        val nextPage: Int? = null,
        @SerializedName("page")
        val page: Int? = null,
        @SerializedName("pagingCounter")
        val pagingCounter: Int? = null,
        @SerializedName("prevPage")
        val prevPage: Int? = null,
        @SerializedName("totalDocs")
        val totalDocs: Int? = null,
        @SerializedName("totalPages")
        val totalPages: Int? = null
    ){

        @Keep
        data class Doc(
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
}