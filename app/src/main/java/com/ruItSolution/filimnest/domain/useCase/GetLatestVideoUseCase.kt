package com.ruItSolution.filimnest.domain.useCase

import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.data.dto.toDomain
import com.ruItSolution.filimnest.domain.model.VideoModel
import com.ruItSolution.filimnest.domain.repo.MovieApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetLatestVideoUseCase @Inject constructor(
    private val repository: MovieApiRepository
) {

    operator fun invoke(): Flow<Resource<List<VideoModel>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getLatestVideo()
            if (response.isSuccessful && response.body() != null) {
                val list = response.body()?.toDomain() ?: emptyList()
                emit(Resource.Success(list))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}