package com.ruItSolution.filimnest.domain.repo

import com.ruItSolution.filimnest.data.dto.ResponseGetCategoryDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetLatestVideoDTO
import retrofit2.Response
import retrofit2.http.Path

interface MovieApiRepository {

    suspend fun getCategoryList(): Response<ResponseGetCategoryDTO>

    suspend fun getLatestVideo(): Response<ResponseGetLatestVideoDTO>

    suspend fun searchVideo(title: String): Response<ResponseGetLatestVideoDTO>

}

