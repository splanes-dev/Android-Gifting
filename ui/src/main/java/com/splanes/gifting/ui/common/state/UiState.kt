package com.splanes.gifting.ui.common.state

sealed class UiState {

    data class Loading(val message: String? = null) : UiState()

    data class Error(val title: String, val cause: String? = null) : UiState()
}
