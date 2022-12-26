package com.splanes.gifting.ui.common.components.input

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Cancel
import androidx.compose.material.icons.rounded.Email
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material.icons.rounded.Person
import androidx.compose.material.icons.rounded.Visibility
import androidx.compose.material.icons.rounded.VisibilityOff
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.remember.rememberStateOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TextInput(
    state: TextInputState,
    visuals: TextInputVisuals,
    modifier: Modifier = Modifier,
    colors: TextFieldColors = TextFieldDefaults.outlinedTextFieldColors()
) {
    val inputValue = state.inputValue
    val coroutineScope = rememberCoroutineScope()
    var focused by rememberStateOf(value = false)
    var plainText by rememberStateOf(value = false)

    Column(modifier) {
        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth()
                .onFocusEvent { focusState -> focused = focusState.hasFocus },
            value = inputValue.text.orEmpty(),
            onValueChange = { value -> coroutineScope.launch { state.onChange(value) } },
            label = { Text(text = visuals.label, style = textStyleOf { bodyMedium }) },
            placeholder = {
                visuals.placeholder?.let { placeholder ->
                    Text(text = placeholder, style = textStyleOf { titleSmall })
                }
            },
            leadingIcon = visuals.leadingIcon?.let { leadingIcon ->
                {
                    Crossfade(targetState = focused) { hasFocus ->
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = leadingIcon,
                            contentDescription = visuals.label,
                            tint = colorOf {
                                when (inputValue) {
                                    is TextInputValue.Normal -> {
                                        if (hasFocus) primary else onSurface.withAlpha(.7)
                                    }

                                    is TextInputValue.Error -> {
                                        if (hasFocus) error else error.withAlpha(.7)
                                    }
                                }
                            }
                        )
                    }
                }
            },
            trailingIcon = {
                when (visuals.inputType) {
                    TextInputVisuals.InputType.Text,
                    TextInputVisuals.InputType.Email -> {
                        AnimatedVisibility(
                            visible = focused && !inputValue.isEmpty,
                            enter = fadeIn(animationSpec = tween(ANIMATION_MILLIS)),
                            exit = fadeOut(animationSpec = tween(ANIMATION_MILLIS))
                        ) {
                            IconButton(onClick = { coroutineScope.launch { state.clear() } }) {
                                Icon(
                                    modifier = Modifier.size(24.dp),
                                    imageVector = Icons.Rounded.Cancel,
                                    contentDescription = stringResource(id = R.string.clear),
                                    tint = colorOf { onSurface.withAlpha(.7) }
                                )
                            }
                        }
                    }

                    TextInputVisuals.InputType.Password -> {
                        AnimatedVisibility(
                            visible = focused,
                            enter = fadeIn(animationSpec = tween(ANIMATION_MILLIS)),
                            exit = fadeOut(animationSpec = tween(ANIMATION_MILLIS))
                        ) {
                            Crossfade(targetState = plainText) { isPlainText ->
                                IconButton(onClick = { plainText = !plainText }) {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = if (isPlainText) {
                                            Icons.Rounded.VisibilityOff
                                        } else {
                                            Icons.Rounded.Visibility
                                        },
                                        contentDescription = stringResource(id = R.string.clear),
                                        tint = colorOf { onSurface.withAlpha(.7) }
                                    )
                                }
                            }
                        }
                    }
                }
            },
            visualTransformation = visuals.transformation(plainText),
            colors = colors,
            keyboardOptions = visuals.keyboardOptions(),
            singleLine = true,
            isError = inputValue is TextInputValue.Error
        )
        AnimatedVisibility(
            visible = inputValue is TextInputValue.Error,
            enter = expandVertically(
                animationSpec = tween(ANIMATION_MILLIS),
                expandFrom = Alignment.Top
            ),
            exit = shrinkVertically(
                animationSpec = tween(ANIMATION_MILLIS),
                shrinkTowards = Alignment.Top
            )
        ) {
            (inputValue as? TextInputValue.Error)?.let { errorValue ->
                Text(
                    modifier = Modifier.padding(top = 4.dp),
                    text = errorValue.error,
                    style = textStyleOf { bodyMedium },
                    color = colorOf { error }
                )
            }
        }
    }
}

private const val ANIMATION_MILLIS = 300

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview
private fun TextInputPreview() {
    GiftingTheme {
        val usernameInputState = rememberTextInputState()
        val emailInputState = rememberTextInputState()
        val passwordInputState = rememberTextInputState()
        val coroutineScope = rememberCoroutineScope()

        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                state = usernameInputState,
                visuals = TextInputVisuals(
                    label = "Username",
                    leadingIcon = Icons.Rounded.Person
                )
            )
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                state = emailInputState,
                visuals = TextInputVisuals(
                    label = "Email",
                    leadingIcon = Icons.Rounded.Email,
                    inputType = TextInputVisuals.InputType.Email
                )
            )
            TextInput(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                state = passwordInputState,
                visuals = TextInputVisuals(
                    label = "Password",
                    leadingIcon = Icons.Rounded.Password,
                    inputType = TextInputVisuals.InputType.Password
                )
            )
            Spacer(modifier = Modifier.height(32.dp))
            Button(onClick = {
                coroutineScope.launch {
                    usernameInputState.validate(
                        listOf(
                            TextInputValidator.of(
                                TextInputValidator.NotBlank,
                                "Mandatory field"
                            )
                        )
                    )
                    emailInputState.validate(
                        listOf(
                            TextInputValidator.of(
                                TextInputValidator.Email,
                                "Invalid email pattern"
                            )
                        )
                    )
                    passwordInputState.validate(
                        listOf(
                            TextInputValidator.of(
                                TextInputValidator.Password,
                                "Weak password. Require 8-length, 1 uppercase and 1 number."
                            )
                        )
                    )
                }
            }) {
                Text(text = "Validate")
            }
        }
    }
}
