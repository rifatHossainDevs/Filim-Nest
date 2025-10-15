package com.ruItSolution.filimnest.data.dto


import com.google.gson.annotations.SerializedName
import androidx.annotation.Keep
import com.ruItSolution.filimnest.domain.model.CategoryModel

@Keep
data class ResponseGetCategoryDTO(
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
        @SerializedName("categoryName")
        val categoryName: String? = null,
        @SerializedName("categoryNo")
        val categoryNo: Int? = null,
        @SerializedName("createdAt")
        val createdAt: String? = null,
        @SerializedName("_id")
        val id: String? = null,
        @SerializedName("updatedAt")
        val updatedAt: String? = null,
        @SerializedName("__v")
        val v: Int? = null
    )
}


fun ResponseGetCategoryDTO.toDomainModel(): List<CategoryModel>? {
    return data?.map {
        CategoryModel(
            categoryName = it?.categoryName.orEmpty(),
            categoryNo = it?.categoryNo ?: 0,
            id = it?.id.orEmpty()
        )
    }
}

