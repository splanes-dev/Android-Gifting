package com.splanes.gifting.ui.feature.profile

import com.splanes.gifting.domain.feature.profile.model.Profile
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.uistate.UiState

sealed interface ProfileUiState : UiState {

    data class ProfileInfo(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val profile: Profile
    ) : ProfileUiState

    data class ProfileInfoEditing(
        override val loading: LoadingVisuals,
        override val error: ErrorVisuals,
        val profile: Profile
    ) : ProfileUiState
}
