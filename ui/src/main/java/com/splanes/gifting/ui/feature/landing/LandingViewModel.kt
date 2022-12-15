package com.splanes.gifting.ui.feature.landing

import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.collectAsState
import com.splanes.gifting.domain.feature.user.model.UserLandingState
import com.splanes.gifting.domain.feature.user.usecase.GetUserLandingStateUseCase
import com.splanes.gifting.ui.common.base.BaseViewModel
import com.splanes.gifting.ui.common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow

@HiltViewModel
class LandingViewModel @Inject constructor(
    private val getUserLandingStateUseCase: GetUserLandingStateUseCase
) : BaseViewModel() {

    private val landing = MutableStateFlow<UserLandingState?>(null)
    val landingState: State<UserLandingState?>
        @Composable
        get() = landing.collectAsState()

    fun getLandingState() {
        launch {
            getUserLandingStateUseCase()
                .onResult(
                    success = {

                    },
                    failure = {
                        error.value = UiState.Error()
                    }
                )
        }
    }
}
