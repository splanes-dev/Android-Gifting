package com.splanes.gifting.ui.feature.authentication

import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiState
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage

sealed interface AuthUiState : UiState {

    val email: String
    val password: String

    object Landing : AuthUiState {
        override val email: String = ""
        override val password: String = ""
        override val loading: LoadingVisuals = LoadingVisuals.Empty
        override val error: ErrorVisuals = ErrorVisuals.Empty
    }

    data class SignIn(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val email: String,
        override val password: String
    ) : AuthUiState

    data class SignUp(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val email: String,
        override val password: String,
        val username: String
    ) : AuthUiState

    data class SignUpWithOnBoarding(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val email: String,
        override val password: String,
        val username: String,
        val onBoardingPages: List<OnBoardingUiPage>
    ) : AuthUiState
}
