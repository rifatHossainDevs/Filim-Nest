package com.ruItSolution.filimnest.presentation.videoDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruItSolution.filimnest.domain.useCase.GetVideoByIdUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetVideoByIdViewModel @Inject constructor(
    private val getVideoByIdUseCase: GetVideoByIdUseCase
): ViewModel() {
    private val _getVideoByIdState = MutableStateFlow(GetVideoByIdState())
    val getVideoByIdState: Flow<GetVideoByIdState> = _getVideoByIdState

    fun getVideoById(id: String) {
        viewModelScope.launch {
            getVideoByIdUseCase(id).collect { result ->
                when (result) {
                    is com.ruItSolution.filimnest.core.Resource.Loading -> {
                        _getVideoByIdState.value = GetVideoByIdState(loading = true)
                    }
                    is com.ruItSolution.filimnest.core.Resource.Success -> {
                        _getVideoByIdState.value = GetVideoByIdState(success = result.data)
                    }
                    is com.ruItSolution.filimnest.core.Resource.Error -> {
                        _getVideoByIdState.value = GetVideoByIdState(error = result.message ?: "An unexpected error occurred")
                    }
                }
            }
        }
    }

}