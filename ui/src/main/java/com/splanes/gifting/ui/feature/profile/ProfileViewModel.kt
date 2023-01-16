package com.splanes.gifting.ui.feature.profile

import com.splanes.gifting.domain.feature.profile.model.Profile
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiViewModel
import com.splanes.gifting.ui.common.uistate.UiViewModelState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

class ProfileViewModelState(
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
                ProfileUiState.ProfileInfo(
                    loading = LoadingVisuals.Visible,
                    error = error,
                    profile = Profile(name = "", email = "", avatarUrl = "")
                )
            }
        }
}

@HiltViewModel
class ProfileViewModel @Inject constructor() : UiViewModel<ProfileUiState, ProfileViewModelState>(
    ProfileViewModelState()
) {

}
