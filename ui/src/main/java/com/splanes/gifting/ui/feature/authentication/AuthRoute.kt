package com.splanes.gifting.ui.feature.authentication

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun AuthRoute(
    viewModel: AuthViewModel,
    onNavToDashboard: () -> Unit
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
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
    onSignIn: (String, String, Boolean) -> Unit,
    onSignUp: (String, String, String, Boolean) -> Unit,
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
