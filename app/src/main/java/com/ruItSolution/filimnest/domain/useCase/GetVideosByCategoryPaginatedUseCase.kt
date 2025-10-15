package com.ruItSolution.filimnest.domain.useCase

import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.data.dto.ResponseGetVideoByCategoryPaginatedDTO
import com.ruItSolution.filimnest.domain.repo.MovieApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetVideosByCategoryPaginatedUseCase @Inject constructor(
    private val repository: MovieApiRepository
) {

    operator fun invoke(
        categoryId: Int,
        page: Int,
        limit: Int
    ): Flow<Resource<ResponseGetVideoByCategoryPaginatedDTO?>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getVideosByCategoryPaginated(
                categoryId = categoryId,
                page = page,
                limit = limit
            )
            if (response.isSuccessful && response.body() != null) {
                val list = response.body()
                emit(Resource.Success(list))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}

