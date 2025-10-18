package com.ruItSolution.filimnest.data.remote

import com.ruItSolution.filimnest.data.dto.ResponseGetCategoryDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetLatestVideoDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByIdDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieApiService {

    @GET("categories/get-all-category")
    suspend fun getCategoryList(): Response<ResponseGetCategoryDTO>


    @GET("videos/get-latest-video")
    suspend fun getLatestVideo(): Response<ResponseGetLatestVideoDTO>

    @GET("videos/search-video/{title}")
    suspend fun searchVideo(
        @Path("title") title: String
    ): Response<ResponseGetLatestVideoDTO>

    @GET("videos/get-video/category-paginated/{categoryId}")
    suspend fun getVideosByCategoryPaginated(
        @Path("categoryId") categoryId: Int,
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): Response<ResponseGetVideoByCategoryPaginatedDTO>

    @GET("videos/get-video-by-id/{id}")
    suspend fun getVideoById(
        @Path("id") id: String
    ): Response<ResponseGetVideoByIdDTO>

}

