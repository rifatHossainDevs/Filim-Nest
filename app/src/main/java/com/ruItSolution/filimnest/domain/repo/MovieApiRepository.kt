package com.ruItSolution.filimnest.domain.repo

import com.ruItSolution.filimnest.data.dto.ResponseGetCategoryDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetLatestVideoDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByIdDTO
import retrofit2.Response
import retrofit2.http.Path

interface MovieApiRepository {

    suspend fun getCategoryList(): Response<ResponseGetCategoryDTO>

    suspend fun getLatestVideo(): Response<ResponseGetLatestVideoDTO>

    suspend fun searchVideo(title: String): Response<ResponseGetLatestVideoDTO>

    suspend fun getVideosByCategoryPaginated(
        categoryId: Int,
        page: Int,
        limit: Int
    ): Response<ResponseGetVideoByCategoryPaginatedDTO>

    suspend fun getVideoById(id: String): Response<ResponseGetVideoByIdDTO>

}

