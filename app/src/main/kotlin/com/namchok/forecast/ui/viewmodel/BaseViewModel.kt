package com.namchok.forecast.ui.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

open class BaseViewModel<T> : ViewModel() {
    val _uiState = MutableStateFlow<BaseUiState<T>>(BaseUiState.Success(null))
    open val uiState: StateFlow<BaseUiState<T>> get() = _uiState.asStateFlow()

    fun setUiStateLoading() {
        _uiState.value = BaseUiState.Loading
    }

    fun setUiStateError(type: BaseErrorType) {
        _uiState.value = BaseUiState.Error(type)
    }

    fun setContent(data: T) {
        _uiState.value = BaseUiState.Success(data)
    }
}

sealed class BaseUiState<out T> {
    object Loading : BaseUiState<Nothing>()

    data class Error(val type: BaseErrorType) : BaseUiState<Nothing>()

    data class Success<out T>(val data: T?) : BaseUiState<T>()
}
