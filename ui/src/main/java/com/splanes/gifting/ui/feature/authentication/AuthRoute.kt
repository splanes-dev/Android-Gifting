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
    Crossfade(screenTypeOf(uiState)) { screenType ->
        when (screenType) {
            AuthScreenType.OnBoarding -> {
                check(uiState is AuthUiState.SignUpWithOnBoarding)
                AuthOnBoardingScreen(uiState, onOnBoardingEnd)
            }

            AuthScreenType.SignUp -> {
                check(uiState is AuthUiState.SignUp)
                AuthSignUpScreen(uiState, onSignUp)
            }

            AuthScreenType.SignIn -> {
                check(uiState is AuthUiState.SignIn)
                AuthSignInScreen(uiState, onSignIn)
            }
        }
    }
}

private enum class AuthScreenType {
    SignUp,
    SignIn,
    OnBoarding
}

private fun screenTypeOf(uiState: AuthUiState) =
    when (uiState) {
        is AuthUiState.SignIn -> AuthScreenType.SignIn
        is AuthUiState.SignUp -> AuthScreenType.SignUp
        is AuthUiState.SignUpWithOnBoarding -> AuthScreenType.OnBoarding
    }
