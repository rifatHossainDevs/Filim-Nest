package com.ruItSolution.filimnest.domain.repo

import com.ruItSolution.filimnest.data.dto.ResponseGetCategoryDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetLatestVideoDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO
import retrofit2.Response

interface MovieApiRepository {

    suspend fun getCategoryList(): Response<ResponseGetCategoryDTO>

    suspend fun getLatestVideo(): Response<ResponseGetLatestVideoDTO>

    suspend fun searchVideo(title: String): Response<ResponseGetLatestVideoDTO>

    suspend fun getVideosByCategoryPaginated(
        categoryId: Int,
        page: Int,
        limit: Int
    ): Response<ResponseGetVideoByCategoryPaginatedDTO>

}

