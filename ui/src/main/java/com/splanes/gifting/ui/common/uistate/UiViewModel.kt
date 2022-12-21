package com.splanes.gifting.ui.common.uistate

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.error.KnownException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

abstract class UiViewModel<T : UiState, V : UiViewModelState<T>>(initialState: V) : ViewModel() {

    protected val viewModelState: MutableStateFlow<V> = MutableStateFlow(initialState)
    val uiState: StateFlow<T> = viewModelState
        .map { viewModelState -> viewModelState.toUiState() }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            viewModelState.value.toUiState()
        )

    protected fun <T> launch(block: suspend () -> T) {
        viewModelScope.launch { block() }
    }

    protected fun <T> UseCase.Result<T>.withSuccess(
        block: (T) -> Unit
    ) = apply {
        when (this) {
            is UseCase.Failure -> {}
            is UseCase.Success -> block(data)
        }
    }

    protected fun <T> UseCase.Result<T>.withFailure(
        block: (KnownException) -> Unit
    ) = apply {
        when (this) {
            is UseCase.Failure -> block(error)
            is UseCase.Success -> {}
        }
    }
}
