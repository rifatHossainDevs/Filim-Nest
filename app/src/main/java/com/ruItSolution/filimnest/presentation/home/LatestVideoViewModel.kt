package com.ruItSolution.filimnest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.domain.useCase.GetLatestVideoUseCase
import com.ruItSolution.filimnest.presentation.search.SearchVideoDataState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LatestVideoViewModel @Inject constructor(
    private val getLatestVideoUseCase: GetLatestVideoUseCase
): ViewModel() {

    private val _latestVideoState = MutableStateFlow(SearchVideoDataState())
    val latestVideoState: Flow<SearchVideoDataState> = _latestVideoState

    fun getLatestVideo() {
        viewModelScope.launch {
            getLatestVideoUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _latestVideoState.value = SearchVideoDataState(error = response.message)
                    }
                    is Resource.Loading -> {
                        _latestVideoState.value = SearchVideoDataState(loading = true)
                    }
                    is Resource.Success -> {
                        _latestVideoState.value = SearchVideoDataState(success = response.data)
                    }
                }
            }
        }
    }

}