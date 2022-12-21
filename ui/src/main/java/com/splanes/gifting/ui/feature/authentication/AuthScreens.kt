package com.splanes.gifting.ui.feature.authentication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.loader.LoaderScaffold
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.column.Weight
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.authentication.components.OnBoardingPageContent
import com.splanes.gifting.ui.feature.authentication.components.OnBoardingPagerProgress
import com.splanes.gifting.ui.feature.authentication.components.SignInForm
import com.splanes.gifting.ui.feature.authentication.components.SignUpForm
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPages
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun AuthSignUpScreen(
    uiState: AuthUiState.SignUp,
    onSignUp: (String, String, String, Boolean) -> Unit
) {
    LoaderScaffold(uiState = uiState) {
        Column(
            modifier = Modifier
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                )
                .background(color = colorOf { primaryContainer.withAlpha(.35) })
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Weight(.5)

            Text(
                text = stringResource(id = R.string.sign_up),
                style = textStyleOf { displayLarge }
            )

            Weight(.5)

            SignUpForm(
                username = uiState.username,
                email = uiState.email,
                password = uiState.password,
                autoSignIn = uiState.autoSignIn,
                onSignUp = onSignUp
            )

            Spacer(24.dp)
        }
    }
}

@Composable
fun AuthLandingScreen() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorOf { primaryContainer.withAlpha(.35) }),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            modifier = Modifier.size(60.dp),
            color = colorOf { onPrimaryContainer }
        )
    }
}

@Composable
fun AuthSignInScreen(
    uiState: AuthUiState.SignIn,
    onSignIn: (String, String, Boolean) -> Unit
) {
    LoaderScaffold(uiState = uiState) {
        Column(
            modifier = Modifier
                .scrollable(
                    state = rememberScrollState(),
                    orientation = Orientation.Vertical
                )
                .background(color = colorOf { primaryContainer.withAlpha(.35) })
                .padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Weight(.5)

            Text(
                text = stringResource(id = R.string.sign_in),
                style = textStyleOf { displayLarge }
            )

            Weight(.5)

            SignInForm(
                email = uiState.email,
                password = uiState.password,
                autoSignIn = uiState.autoSignIn,
                onSignIn = onSignIn
            )

            Spacer(24.dp)
        }
    }
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthOnBoardingScreen(
    uiState: AuthUiState.SignUpWithOnBoarding,
    onOnBoardingEnd: () -> Unit
) {
    val pagerState = rememberPagerState()
    val page = uiState.onBoardingPages[pagerState.currentPage]
    LoaderScaffold(uiState = uiState) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(colorOf { page.background(this) }.withAlpha(.4)),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Weight()

            HorizontalPager(
                state = pagerState,
                count = uiState.onBoardingPages.count()
            ) {
                OnBoardingPageContent(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    page = page
                )
            }

            Weight()

            OnBoardingPagerProgress(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth(),
                pagerState = pagerState,
                currentPage = page,
                onOnBoardingEnd = onOnBoardingEnd
            )

            Spacer(height = 16.dp)
        }
    }
}

@Preview("Auth SignUp screen", device = Devices.PIXEL_C)
@Preview("Auth SignUp screen (dark)", uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Auth SignUp screen (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewAuthSignUpScreen() {
    GiftingTheme {
        AuthSignUpScreen(
            uiState = AuthUiState.SignUp(
                loading = LoadingVisuals.Empty,
                error = ErrorVisuals.Empty,
                email = "",
                password = "",
                username = "",
                autoSignIn = false
            ),
            onSignUp = { _, _, _, _ -> }
        )
    }
}

@Preview("Auth SignIn screen", device = Devices.PIXEL_C)
@Preview("Auth SignIn screen (dark)", uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Auth SignIn screen (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewAuthSignInScreen() {
    GiftingTheme {
        AuthSignInScreen(
            uiState = AuthUiState.SignIn(
                loading = LoadingVisuals.Empty,
                error = ErrorVisuals.Empty,
                email = "",
                password = "",
                autoSignIn = false
            ),
            onSignIn = { _, _, _ -> }
        )
    }
}

@Preview("Auth OnBoarding screen", device = Devices.PIXEL_C)
@Preview("Auth OnBoarding screen (dark)", uiMode = UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Auth OnBoarding screen (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
fun PreviewAuthOnBoardingScreen() {
    GiftingTheme {
        AuthOnBoardingScreen(
            uiState = AuthUiState.SignUpWithOnBoarding(
                loading = LoadingVisuals.Empty,
                error = ErrorVisuals.Empty,
                email = "",
                password = "",
                username = "",
                onBoardingPages = OnBoardingUiPages().toList()
            ),
            onOnBoardingEnd = {}
        )
    }
}
