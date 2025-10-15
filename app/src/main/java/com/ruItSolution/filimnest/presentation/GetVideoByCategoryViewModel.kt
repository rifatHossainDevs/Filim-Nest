package com.ruItSolution.filimnest.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.domain.useCase.GetVideosByCategoryPaginatedUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetVideoByCategoryViewModel @Inject constructor(
    private val getVideoByCategoryUseCase: GetVideosByCategoryPaginatedUseCase
) : ViewModel() {
    private val _getVideoByCategoryState = MutableStateFlow(GetVideoByCategoryDataState())
    val getVideoByCategoryState: Flow<GetVideoByCategoryDataState> = _getVideoByCategoryState

    fun getVideosByCategory(
        categoryId: Int,
        page: Int,
        limit: Int
    ) {
        viewModelScope.launch {
            getVideoByCategoryUseCase.invoke(
                categoryId = categoryId,
                page = page,
                limit = limit
            ).collect { response ->

                when (response) {
                    is Resource.Error -> {
                        _getVideoByCategoryState.value =
                            GetVideoByCategoryDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _getVideoByCategoryState.value = GetVideoByCategoryDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _getVideoByCategoryState.value =
                            GetVideoByCategoryDataState(success = response.data)
                    }
                }

            }
        }
    }


}