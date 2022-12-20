package com.splanes.gifting.ui.feature.authentication

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.input.Email
import com.splanes.gifting.ui.common.components.input.NotBlank
import com.splanes.gifting.ui.common.components.input.Password
import com.splanes.gifting.ui.common.components.input.TextInput
import com.splanes.gifting.ui.common.components.input.TextInputValidator
import com.splanes.gifting.ui.common.components.input.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.of
import com.splanes.gifting.ui.common.components.input.rememberTextInputState
import com.splanes.gifting.ui.common.components.pagerindicator.PagerIndicators
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.column.Weight
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.uistate.ErrorVisuals
import com.splanes.gifting.ui.common.uistate.LoadingVisuals
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.remember.rememberStateOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.authentication.model.OnBoardingUiPages
import com.splanes.gifting.ui.theme.GiftingTheme
import kotlinx.coroutines.launch

@Composable
fun AuthSignUpScreen(
    uiState: AuthUiState.SignUp,
    onSignUp: (String, String, String, Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val usernameState = rememberTextInputState(uiState.username)
    val emailState = rememberTextInputState(uiState.email)
    val passwordState = rememberTextInputState(uiState.password)
    var rememberMeState by rememberStateOf(value = uiState.autoSignIn)
    val usernameValidators = listOf(
        TextInputValidator.of(
            TextInputValidator.NotBlank,
            stringResource(id = R.string.field_mandatory)
        )
    )
    val emailValidators = listOf(
        TextInputValidator.of(
            TextInputValidator.Email,
            stringResource(id = R.string.field_invalid_email_format)
        )
    )
    val passwordValidators = listOf(
        TextInputValidator.of(
            TextInputValidator.Password,
            stringResource(id = R.string.field_password_weak)
        )
    )
    Column(
        modifier = Modifier
            .scrollable(
                state = rememberScrollState(),
                orientation = Orientation.Vertical
            )
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Weight(.5)
        Text(
            text = stringResource(id = R.string.sign_up),
            style = textStyleOf { displayLarge }
        )
        Weight(.5)
        TextInput(
            state = usernameState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.username),
                leadingIcon = Icons.Rounded.AccountCircle,
                inputType = TextInputVisuals.InputType.Text,
                imeAction = ImeAction.Next
            )
        )
        Spacer(height = 8.dp)
        TextInput(
            state = emailState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.email),
                leadingIcon = Icons.Rounded.Mail,
                inputType = TextInputVisuals.InputType.Email,
                imeAction = ImeAction.Next
            )
        )
        Spacer(height = 8.dp)
        TextInput(
            state = passwordState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.password),
                leadingIcon = Icons.Rounded.Password,
                inputType = TextInputVisuals.InputType.Password,
                imeAction = ImeAction.Default
            )
        )
        Spacer(height = 8.dp)
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = stringResource(id = R.string.remember_me),
                style = textStyleOf { titleMedium }
            )
            Weight()
            Switch(
                checked = rememberMeState,
                onCheckedChange = { rememberMeState = !rememberMeState }
            )
        }
        Weight()
        GiftingButton(
            text = stringResource(id = R.string.sign_me_up),
            onClick = {
                coroutineScope.launch {
                    if (usernameState.validate(usernameValidators) &&
                        emailState.validate(emailValidators) &&
                        passwordState.validate(passwordValidators)
                    ) {
                        onSignUp(
                            usernameState.inputValue.text.orEmpty(),
                            emailState.inputValue.text.orEmpty(),
                            passwordState.inputValue.text.orEmpty(),
                            rememberMeState
                        )
                    }
                }
            }
        )
        Spacer(24.dp)
    }
}

@Composable
fun AuthSignInScreen() {
}

@Composable
fun AuthAutoSigningInScreen() {
}

@OptIn(ExperimentalPagerApi::class)
@Composable
fun AuthOnBoardingScreen(
    uiState: AuthUiState.SignUpWithOnBoarding,
    onOnBoardingEnd: () -> Unit
) {
    val pagerState = rememberPagerState()
    val page = uiState.onBoardingPages[pagerState.currentPage]
    val isLastPage = pagerState.currentPage == pagerState.pageCount - 1
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
            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = page.image),
                    contentDescription = stringResource(id = page.title)
                )

                Spacer(height = 32.dp)

                Text(
                    text = stringResource(id = page.title),
                    style = textStyleOf { headlineLarge },
                    color = colorOf { contentColorFor(backgroundColor = page.background(this)) }
                )

                Spacer(height = 24.dp)

                Text(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    text = stringResource(id = page.description),
                    style = textStyleOf { bodyLarge },
                    color = colorOf { contentColorFor(backgroundColor = page.background(this)) },
                    textAlign = TextAlign.Center
                )
            }
        }
        Weight()
        Row(
            modifier = Modifier
                .wrapContentHeight()
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Spacer(width = 16.dp)

            AnimatedVisibility(visible = !isLastPage) {
                GiftingTextButton(
                    text = stringResource(id = R.string.skip),
                    colors = ButtonDefaults.textButtonColors(
                        contentColor = colorOf {
                            contentColorFor(page.background(this))
                        }
                    ),
                    onClick = onOnBoardingEnd
                )
            }
            Weight(if (isLastPage) 2.0 else 1.0)
            PagerIndicators(
                pagerState = pagerState,
                indicatorColor = colorOf {
                    contentColorFor(
                        backgroundColor = page.background(this).withAlpha(.7)
                    )
                }
            )
            Weight(if (!isLastPage) 2.0 else 1.0)
            AnimatedVisibility(
                modifier = Modifier.padding(end = 16.dp),
                visible = isLastPage
            ) {
                GiftingButton(
                    text = stringResource(id = R.string.sign_up),
                    onClick = onOnBoardingEnd,
                    colors = ButtonDefaults.buttonColors(
                        contentColor = colorOf {
                            contentColorFor(page.background(this))
                        }
                    )
                )
            }
        }
        Spacer(height = 16.dp)
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
