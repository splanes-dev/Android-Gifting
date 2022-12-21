package com.splanes.gifting.ui.feature.authentication

import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.usecase.GetAuthStateUseCase
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPages
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update

data class AuthUiViewModelState(
    private val isLandingVisible: Boolean = true,
    private val error: ErrorVisuals = ErrorVisuals.Empty,
    private val loading: LoadingVisuals = LoadingVisuals.Empty,
    private val isSignedUp: Boolean = false,
    private val email: String = "",
    private val username: String = "",
    private val password: String = "",
    private val autoSignIn: Boolean = false,
    private val onBoardingPages: List<OnBoardingUiPage> = emptyList()
) : UiViewModelState<AuthUiState> {
    override fun toUiState(): AuthUiState =
        when {
            isLandingVisible -> {
                AuthUiState.Landing
            }
            isSignedUp -> {
                AuthUiState.SignIn(
                    error = error,
                    loading = loading,
                    email = email,
                    password = password,
                    autoSignIn = autoSignIn
                )
            }

            onBoardingPages.isEmpty() -> {
                AuthUiState.SignUp(
                    loading = loading,
                    error = error,
                    email = email,
                    password = password,
                    username = username,
                    autoSignIn = autoSignIn
                )
            }

            else -> {
                AuthUiState.SignUpWithOnBoarding(
                    loading = loading,
                    error = error,
                    email = email,
                    password = password,
                    username = username,
                    onBoardingPages = onBoardingPages
                )
            }
        }
}

@HiltViewModel
class AuthViewModel @Inject constructor(
    private val getAuthState: GetAuthStateUseCase
) : UiViewModel<AuthUiState, AuthUiViewModelState>(
    AuthUiViewModelState()
) {
    init {
        launchGetAuthState()
    }

    private fun launchGetAuthState() {
        launch {
            getAuthState()
                .withSuccess { result ->
                    viewModelState.update { state ->
                        state.copy(
                            isLandingVisible = false,
                            loading = LoadingVisuals(visible = false),
                            isSignedUp = result.isSignedUp(),
                            autoSignIn = result == AuthStateValue.AutoSignIn,
                            onBoardingPages = if (result == AuthStateValue.OnBoarding) {
                                OnBoardingUiPages().toList()
                            } else {
                                emptyList()
                            }
                        )
                    }
                }
                .withFailure { error ->
                    viewModelState.update { state ->
                        state.copy(
                            isLandingVisible = false,
                            loading = LoadingVisuals(visible = false)
                            // error = ErrorVisuals(TODO)
                        )
                    }
                }
        }
    }

    fun signIn(email: String, password: String, remember: Boolean) {
    }

    fun signUp(email: String, password: String, username: String, remember: Boolean) {
    }

    fun onOnBoardingEnd() {
        viewModelState.update { state ->
            state.copy(onBoardingPages = emptyList())
        }
    }
}
