package com.splanes.gifting.ui.feature.authentication

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.splanes.gifting.ui.feature.authentication.model.SignInFormData
import com.splanes.gifting.ui.feature.authentication.model.SignUpFormData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AuthRoute(
    viewModel: AuthViewModel,
    onNavToDashboard: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val uiSideEffect by viewModel.authSideEffect.collectAsStateWithLifecycle()

    LaunchedEffect(uiSideEffect) {
        when (uiSideEffect) {
            AuthUiSideEffect.None -> {
                // Should not be possible
            }

            AuthUiSideEffect.NavToDashboard -> onNavToDashboard()
        }
    }

    AuthRoute(
        uiState = uiState,
        onSignIn = viewModel::signIn,
        onSignUp = viewModel::signUp,
        onOnBoardingEnd = viewModel::onOnBoardingEnd
    )
}

@Composable
fun AuthRoute(
    uiState: AuthUiState,
    onSignIn: (SignInFormData) -> Unit,
    onSignUp: (SignUpFormData) -> Unit,
    onOnBoardingEnd: () -> Unit
) {
    Crossfade(uiState) { screenUiState ->
        when (screenUiState) {
            AuthUiState.Landing -> AuthLandingScreen()
            is AuthUiState.SignIn -> AuthSignInScreen(screenUiState, onSignIn)
            is AuthUiState.SignUp -> AuthSignUpScreen(screenUiState, onSignUp)
            is AuthUiState.SignUpWithOnBoarding ->
                AuthOnBoardingScreen(screenUiState, onOnBoardingEnd)
        }
    }
}
