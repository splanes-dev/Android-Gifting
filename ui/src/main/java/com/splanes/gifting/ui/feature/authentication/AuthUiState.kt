package com.splanes.gifting.ui.feature.authentication

import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiState
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage

sealed interface AuthUiState : UiState {

    val email: String
    val password: String

    data class SignIn(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val email: String,
        override val password: String,
        val autoSignIn: Boolean
    ) : AuthUiState

    data class SignUp(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        override val email: String,
        override val password: String,
        val username: String,
        val autoSignIn: Boolean
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