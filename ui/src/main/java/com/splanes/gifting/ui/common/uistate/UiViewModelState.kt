package com.splanes.gifting.ui.common.uistate

interface UiViewModelState<T : UiState> {
    fun toUiState(): T
}
