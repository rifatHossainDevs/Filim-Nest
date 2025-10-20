package com.ruItSolution.filimnest.domain.useCase

import android.util.Log
import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.data.dto.toDomainModel
import com.ruItSolution.filimnest.domain.model.CategoryModel
import com.ruItSolution.filimnest.domain.repo.MovieApiRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetCategoryListUseCase @Inject constructor(
    private val repository: MovieApiRepository
) {

    operator fun invoke(): Flow<Resource<List<CategoryModel>>> = flow {
        try {
            emit(Resource.Loading())
            val response = repository.getCategoryList()
            if (response.isSuccessful && response.body() != null) {
                val list = response.body()?.toDomainModel() ?: emptyList()
                emit(Resource.Success(list))
            } else {
                emit(Resource.Error(response.message()))
            }
        } catch (e: Exception) {
            emit(Resource.Error(e.localizedMessage ?: "An unexpected error occurred"))
        }
    }
}

