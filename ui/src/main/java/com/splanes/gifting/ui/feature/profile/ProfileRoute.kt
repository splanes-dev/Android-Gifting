package com.splanes.gifting.ui.feature.profile

import androidx.compose.animation.Crossfade
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.lifecycle.compose.ExperimentalLifecycleComposeApi
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.splanes.gifting.ui.feature.profile.model.ProfileBasicInfoEditFormData

@OptIn(ExperimentalLifecycleComposeApi::class)
@Composable
fun ProfileRoute(viewModel: ProfileViewModel) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    ProfileRoute(
        uiState = uiState,
        onLoadProfile = viewModel::onLoadProfile,
        onEditProfile = viewModel::onStartEdit,
        onEditProfileForm = viewModel::onEditProfile,
        onCancelEdit = viewModel::onCancelEditProfile
    )
}

@Composable
fun ProfileRoute(
    uiState: ProfileUiState,
    onLoadProfile: () -> Unit,
    onEditProfile: () -> Unit,
    onEditProfileForm: (ProfileBasicInfoEditFormData) -> Unit,
    onCancelEdit: () -> Unit
) {
    Crossfade(targetState = uiState) { profileUiState ->
        when (profileUiState) {
            is ProfileUiState.ProfileEmpty ->
                ProfileEmptyScreen(
                    uiState = profileUiState,
                    onReloadProfile = onLoadProfile
                )

            is ProfileUiState.ProfileInfo ->
                ProfileInfoScreen(
                    uiState = profileUiState,
                    onEditProfile = onEditProfile
                )

            is ProfileUiState.ProfileInfoEditing ->
                ProfileInfoEditingScreen(
                    uiState = profileUiState,
                    onEditProfile = onEditProfileForm,
                    onCancel = onCancelEdit
                )
        }
    }
}
