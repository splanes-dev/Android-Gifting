package com.splanes.gifting.ui.feature.profile

import com.splanes.gifting.domain.feature.profile.model.Profile
import com.splanes.gifting.domain.feature.profile.request.UpdateProfileRequest
import com.splanes.gifting.domain.feature.profile.usecase.GetProfileUseCase
import com.splanes.gifting.domain.feature.profile.usecase.UpdateProfileUseCase
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import com.splanes.gifting.ui.feature.profile.model.ProfileBasicInfoEditFormData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.update

data class ProfileViewModelState(
    private val loading: LoadingVisuals = LoadingVisuals.Hidden,
    private val error: ErrorVisuals = ErrorVisuals.Empty,
    private val profile: Profile? = null,
    private val isEditingProfile: Boolean = false
) : UiViewModelState<ProfileUiState> {
    override fun toUiState(): ProfileUiState =
        when {
            profile != null -> {
                if (isEditingProfile) {
                    ProfileUiState.ProfileInfoEditing(
                        loading = loading,
                        error = error,
                        profile = profile
                    )
                } else {
                    ProfileUiState.ProfileInfo(
                        loading = loading,
                        error = error,
                        profile = profile
                    )
                }
            }

            else -> {
                ProfileUiState.ProfileEmpty(
                    loading = loading,
                    error = error
                )
            }
        }
}

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val getProfile: GetProfileUseCase,
    private val updateProfile: UpdateProfileUseCase
) : UiViewModel<ProfileUiState, ProfileViewModelState>(ProfileViewModelState()) {

    init {
        onLoadProfile()
    }

    fun onLoadProfile() {
        viewModelState.update { state -> state.copy(loading = LoadingVisuals.Hidden) }
        launch {
            getProfile()
                .then { profile ->
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden,
                            profile = profile
                        )
                    }
                }
                .catch {
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden
                            // Todo: error
                        )
                    }
                }
        }
    }

    fun onStartEdit() {
        viewModelState.update { state ->
            state.copy(isEditingProfile = true)
        }
    }

    fun onEditProfile(form: ProfileBasicInfoEditFormData) {
        viewModelState.update { state -> state.copy(loading = LoadingVisuals.Visible) }
        launch {
            val request = UpdateProfileRequest(
                username = form.username,
                password = form.password
            )
            updateProfile(request)
                .then { profile ->
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden,
                            isEditingProfile = false,
                            profile = profile
                        )
                    }
                }
                .catch {
                    viewModelState.update { state ->
                        state.copy(
                            loading = LoadingVisuals.Hidden
                            // Todo error
                        )
                    }
                }
        }
    }

    fun onCancelEditProfile() {
        viewModelState.update { state ->
            state.copy(isEditingProfile = false)
        }
    }
}
