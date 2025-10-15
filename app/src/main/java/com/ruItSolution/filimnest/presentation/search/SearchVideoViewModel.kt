package com.ruItSolution.filimnest.presentation.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.domain.useCase.SearchVideoUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchVideoViewModel @Inject constructor(
    private val searchVideoUseCase: SearchVideoUseCase
): ViewModel() {

    private val _searchVideoState = MutableStateFlow(SearchVideoDataState())
    val searchVideoState: Flow<SearchVideoDataState> = _searchVideoState

    fun searchVideo(title: String) {
        viewModelScope.launch {
            searchVideoUseCase.invoke(title).collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _searchVideoState.value = SearchVideoDataState(error = response.message)
                    }
                    is Resource.Loading -> {
                        _searchVideoState.value = SearchVideoDataState(loading = true)
                    }
                    is Resource.Success -> {
                        _searchVideoState.value = SearchVideoDataState(success = response.data)
                    }
                }
            }
        }
    }
}