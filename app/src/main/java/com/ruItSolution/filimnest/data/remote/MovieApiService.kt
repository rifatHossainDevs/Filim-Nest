package com.ruItSolution.filimnest.data.remote

import com.ruItSolution.filimnest.data.dto.ResponseGetCategoryDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetLatestVideoDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface MovieApiService {

    @GET("categories/get-all-category")
    suspend fun getCategoryList(): Response<ResponseGetCategoryDTO>


    @GET("videos/get-latest-video")
    suspend fun getLatestVideo(): Response<ResponseGetLatestVideoDTO>

    @GET("videos/search-video/{title}")
    suspend fun searchVideo(
        @Path("title") title: String
    ): Response<ResponseGetLatestVideoDTO>

}

