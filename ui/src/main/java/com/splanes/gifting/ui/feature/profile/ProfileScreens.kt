package com.splanes.gifting.ui.feature.profile

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.loader.LoaderScaffold
import com.splanes.gifting.ui.common.components.topbar.GiftingTopBar
import com.splanes.gifting.ui.feature.profile.components.ProfileBasicInfoEditForm
import com.splanes.gifting.ui.feature.profile.components.ProfileHeader
import com.splanes.gifting.ui.feature.profile.model.ProfileBasicInfoEditFormData

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileEmptyScreen(
    uiState: ProfileUiState.ProfileEmpty,
    onReloadProfile: () -> Unit
) {
    LoaderScaffold(uiState = uiState) {
        Scaffold(
            topBar = { GiftingTopBar(title = stringResource(id = R.string.profile)) }
        ) { innerPaddings ->
            Column(
                modifier = Modifier.padding(innerPaddings),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                GiftingButton(
                    text = stringResource(id = R.string.reload),
                    onClick = onReloadProfile
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfoScreen(
    uiState: ProfileUiState.ProfileInfo,
    onEditProfile: () -> Unit = {}
) {
    LoaderScaffold(uiState = uiState) {
        Scaffold(
            topBar = { GiftingTopBar(title = stringResource(id = R.string.profile)) }
        ) { innerPaddings ->
            Column(
                modifier = Modifier.padding(innerPaddings),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                ProfileHeader(
                    modifier = Modifier
                        .padding(
                            vertical = 24.dp,
                            horizontal = 16.dp
                        ),
                    profile = uiState.profile,
                    onEditClick = onEditProfile
                )
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileInfoEditingScreen(
    uiState: ProfileUiState.ProfileInfoEditing,
    onEditProfile: (ProfileBasicInfoEditFormData) -> Unit,
    onCancel: () -> Unit
) {
    LoaderScaffold(uiState = uiState) {
        Scaffold(
            topBar = { GiftingTopBar(title = stringResource(id = R.string.profile)) }
        ) { innerPaddings ->
            ProfileBasicInfoEditForm(
                modifier = Modifier
                    .padding(innerPaddings)
                    .padding(vertical = 24.dp, horizontal = 16.dp),
                profile = uiState.profile,
                onEdit = onEditProfile,
                onCancel = onCancel
            )
        }
    }
}
