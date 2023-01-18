package com.splanes.gifting.ui.feature.authentication

import androidx.lifecycle.viewModelScope
import com.splanes.gifting.domain.feature.auth.model.AuthStateValue
import com.splanes.gifting.domain.feature.auth.request.SignInRequest
import com.splanes.gifting.domain.feature.auth.request.SignUpRequest
import com.splanes.gifting.domain.feature.auth.usecase.AutoSignInUseCase
import com.splanes.gifting.domain.feature.auth.usecase.GetAuthStateUseCase
import com.splanes.gifting.domain.feature.auth.usecase.SignInUseCase
import com.splanes.gifting.domain.feature.auth.usecase.SignUpUseCase
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPage
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPages
import com.splanes.gifting.ui.feature.authentication.model.SignInFormData
import com.splanes.gifting.ui.feature.authentication.model.SignUpFormData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

data class AuthUiViewModelState(
    private val isLandingVisible: Boolean = true,
    private val error: ErrorVisuals = ErrorVisuals.Empty,
    private val loading: LoadingVisuals = LoadingVisuals.Hidden,
    private val isSignedUp: Boolean = false,
    private val email: String = "",
    private val username: String = "",
    private val password: String = "",
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
                    password = password
                )
            }

            onBoardingPages.isEmpty() -> {
                AuthUiState.SignUp(
                    loading = loading,
                    error = error,
                    email = email,
                    password = password,
                    username = username
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
    private val getAuthState: GetAuthStateUseCase,
    private val signUp: SignUpUseCase,
    private val signIn: SignInUseCase,
    private val autoSignIn: AutoSignInUseCase
) : UiViewModel<AuthUiState, AuthUiViewModelState>(
    AuthUiViewModelState()
) {
    private val _authSideEffect = MutableStateFlow(AuthUiSideEffect.None)
    val authSideEffect: StateFlow<AuthUiSideEffect> = _authSideEffect
        .filter { it != AuthUiSideEffect.None }
        .stateIn(
            viewModelScope,
            SharingStarted.Eagerly,
            AuthUiSideEffect.None
        )

    init {
        launchGetAuthState()
    }

    private fun launchGetAuthState() {
        launch {
            getAuthState()
                .then { result ->
                    if (result == AuthStateValue.AutoSignIn) {
                        launchAutoSignIn()
                    } else {
                        viewModelState.update { state ->
                            state.copy(
                                isLandingVisible = false,
                                isSignedUp = result.isSignedUp(),
                                onBoardingPages = if (result == AuthStateValue.OnBoarding) {
                                    OnBoardingUiPages().toList()
                                } else {
                                    emptyList()
                                }
                            )
                        }
                    }
                }
                .catch { error ->
                    viewModelState.update { state ->
                        state.copy(
                            isLandingVisible = false
                            // error = ErrorVisuals(TODO)
                        )
                    }
                }
        }
    }

    private fun launchAutoSignIn() {
        launch {
            autoSignIn().then {
                _authSideEffect.update { AuthUiSideEffect.NavToDashboard }
            }.catch {
                viewModelState.update { state ->
                    state.copy(
                        isLandingVisible = false
                        // error = ErrorVisuals(TODO)
                    )
                }
            }
        }
    }

    fun signIn(form: SignInFormData) {
        launch {
            viewModelState.update { state -> state.copy(loading = LoadingVisuals.Visible) }
            signIn(
                SignInRequest(
                    email = form.email,
                    password = form.password,
                    autoSignIn = form.autoSignIn
                )
            ).then {
                _authSideEffect.update { AuthUiSideEffect.NavToDashboard }
            }.catch {
                viewModelState.update { state ->
                    state.copy(
                        isLandingVisible = false
                        // error = ErrorVisuals(TODO)
                    )
                }
            }
        }
    }

    fun signUp(form: SignUpFormData) {
        launch {
            viewModelState.update { state -> state.copy(loading = LoadingVisuals.Visible) }
            signUp(
                SignUpRequest(
                    username = form.username,
                    email = form.email,
                    password = form.password,
                    autoSignIn = form.autoSignIn
                )
            ).then {
                _authSideEffect.update { AuthUiSideEffect.NavToDashboard }
            }.catch {
                viewModelState.update { state ->
                    state.copy(
                        isLandingVisible = false
                        // error = ErrorVisuals(TODO)
                    )
                }
            }
        }
    }

    fun onOnBoardingEnd() {
        viewModelState.update { state ->
            state.copy(onBoardingPages = emptyList())
        }
    }

    fun onNavToSignIn() {
        viewModelState.update { state ->
            state.copy(isSignedUp = true)
        }
    }

    fun onNavToSignUp() {
        viewModelState.update { state ->
            state.copy(isSignedUp = false)
        }
    }
}
