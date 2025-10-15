package com.ruItSolution.filimnest.data.repoImpl

import com.ruItSolution.filimnest.data.dto.ResponseGetCategoryDTO
import com.ruItSolution.filimnest.data.dto.ResponseGetLatestVideoDTO
import com.ruItSolution.filimnest.data.remote.MovieApiService
import com.ruItSolution.filimnest.domain.repo.MovieApiRepository
import retrofit2.Response
import javax.inject.Inject

class MovieApiRepositoryImpl @Inject constructor(
    private val movieApiService: MovieApiService
): MovieApiRepository {

    override suspend fun getCategoryList(): Response<ResponseGetCategoryDTO> {
        return movieApiService.getCategoryList()
    }

    override suspend fun getLatestVideo(): Response<ResponseGetLatestVideoDTO> {
        return movieApiService.getLatestVideo()
    }

    override suspend fun searchVideo(title: String): Response<ResponseGetLatestVideoDTO> {
        return movieApiService.searchVideo(title)
    }

}

