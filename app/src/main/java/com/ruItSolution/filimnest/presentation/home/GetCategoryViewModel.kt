package com.ruItSolution.filimnest.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ruItSolution.filimnest.core.Resource
import com.ruItSolution.filimnest.domain.useCase.GetCategoryListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GetCategoryViewModel @Inject constructor(
    private val getCategoryListUseCase: GetCategoryListUseCase
): ViewModel(){

    private val _categoryListState = MutableStateFlow(CategoryListDataState())
    val categoryListState: Flow<CategoryListDataState> = _categoryListState

    fun getCategoryList(){
        viewModelScope.launch {
            getCategoryListUseCase.invoke().collect { response ->
                when (response) {
                    is Resource.Error -> {
                        _categoryListState.value = CategoryListDataState(error = response.message)
                    }
                    is Resource.Loading -> {
                        _categoryListState.value = CategoryListDataState(loading = true)
                    }
                    is Resource.Success -> {
                        _categoryListState.value = CategoryListDataState(success = response.data)
                    }
                }
            }
        }
    }

}