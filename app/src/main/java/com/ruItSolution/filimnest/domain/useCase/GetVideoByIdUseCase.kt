package com.ruItSolution.filimnest.domain.useCase

import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.data.dto.toDomain
import com.ruItSolution.filimnest.domain.model.VideoModel
import com.ruItSolution.filimnest.domain.repo.MovieApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideoByIdUseCase @Inject constructor(
    private val repository: MovieApiRepository
) {

    operator fun invoke(id: String): Flow<Resource<VideoModel?>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getVideoById(id)
            if (response.isSuccessful && response.body() != null) {
                val data = response.body()?.toDomain()
                emit(Resource.Success(data))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }

}