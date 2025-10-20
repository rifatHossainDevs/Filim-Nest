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

    fun resetState() {
        _getVideoByCategoryState.value = GetVideoByCategoryDataState()
    }

    private val _getHollywoodVideoState = MutableStateFlow(GetVideoByCategoryDataState())
    val getHollywoodVideoState: Flow<GetVideoByCategoryDataState> = _getHollywoodVideoState

    fun getHollywoodVideosByCategory(
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
                        _getHollywoodVideoState.value =
                            GetVideoByCategoryDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _getHollywoodVideoState.value = GetVideoByCategoryDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _getHollywoodVideoState.value =
                            GetVideoByCategoryDataState(success = response.data)
                    }
                }

            }
        }
    }


    private val _getBollywoodVideoState = MutableStateFlow(GetVideoByCategoryDataState())
    val getBollywoodVideoState: Flow<GetVideoByCategoryDataState> = _getBollywoodVideoState

    fun getBollywoodVideosByCategory(
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
                        _getBollywoodVideoState.value =
                            GetVideoByCategoryDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _getBollywoodVideoState.value = GetVideoByCategoryDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _getBollywoodVideoState.value =
                            GetVideoByCategoryDataState(success = response.data)
                    }
                }

            }
        }
    }


    private val _getHorrorVideoState = MutableStateFlow(GetVideoByCategoryDataState())
    val getHorrorVideoState: Flow<GetVideoByCategoryDataState> = _getHorrorVideoState

    fun getHorrorVideosByCategory(
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
                        _getHorrorVideoState.value =
                            GetVideoByCategoryDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _getHorrorVideoState.value = GetVideoByCategoryDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _getHorrorVideoState.value =
                            GetVideoByCategoryDataState(success = response.data)
                    }
                }

            }
        }
    }


    private val _getAnimationVideoState = MutableStateFlow(GetVideoByCategoryDataState())
    val getAnimationVideoState: Flow<GetVideoByCategoryDataState> = _getAnimationVideoState

    fun getAnimationVideosByCategory(
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
                        _getAnimationVideoState.value =
                            GetVideoByCategoryDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _getAnimationVideoState.value = GetVideoByCategoryDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _getAnimationVideoState.value =
                            GetVideoByCategoryDataState(success = response.data)
                    }
                }

            }
        }
    }


    private val _getTamilVideoState = MutableStateFlow(GetVideoByCategoryDataState())
    val getTamilVideoState: Flow<GetVideoByCategoryDataState> = _getTamilVideoState

    fun getTamilVideosByCategory(
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
                        _getTamilVideoState.value =
                            GetVideoByCategoryDataState(error = response.message)
                    }

                    is Resource.Loading -> {
                        _getTamilVideoState.value = GetVideoByCategoryDataState(loading = true)
                    }

                    is Resource.Success -> {
                        _getTamilVideoState.value =
                            GetVideoByCategoryDataState(success = response.data)
                    }
                }

            }
        }
    }


}