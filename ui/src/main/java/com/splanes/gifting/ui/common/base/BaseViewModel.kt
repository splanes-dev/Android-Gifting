package com.splanes.gifting.ui.common.base

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.splanes.gifting.domain.common.base.usecase.UseCase
import com.splanes.gifting.domain.common.error.KnownException
import com.splanes.gifting.ui.common.state.UiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

abstract class BaseViewModel : ViewModel() {

    private val loading: MutableStateFlow<UiState.Loading?> = MutableStateFlow(null)
    val loadingState: State<UiState.Loading?>
        @Composable
        get() = loading.collectAsState()

    private val error: MutableStateFlow<UiState.Error?> = MutableStateFlow(null)
    val errorState: State<UiState.Error?>
        @Composable
        get() = error.collectAsState()

    protected fun <T> launch(block: suspend () -> T) {
        viewModelScope.launch { block() }
    }

    protected suspend fun <T> Flow<UseCase.Result<T>>.onResult(
        failure: (KnownException) -> Unit = {},
        success: (T) -> Unit
    ) = distinctUntilChanged().collect { result ->
        when (result) {
            is UseCase.Failure -> failure(result.error)
            is UseCase.Success -> success(result.data)
        }
    }
}
