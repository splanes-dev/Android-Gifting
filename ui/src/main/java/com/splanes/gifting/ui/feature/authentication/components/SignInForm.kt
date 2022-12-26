package com.splanes.gifting.ui.feature.authentication.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.input.Email
import com.splanes.gifting.ui.common.components.input.Password
import com.splanes.gifting.ui.common.components.input.TextInput
import com.splanes.gifting.ui.common.components.input.TextInputValidator
import com.splanes.gifting.ui.common.components.input.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.of
import com.splanes.gifting.ui.common.components.input.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.column.Weight
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.remember.rememberStateOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColumnScope.SignInForm(
    email: String,
    password: String,
    autoSignIn: Boolean,
    onSignIn: (String, String, Boolean) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val emailState = rememberTextInputState(email)
    val passwordState = rememberTextInputState(password)
    var rememberMeState by rememberStateOf(value = autoSignIn)
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
        text = stringResource(id = R.string.sign_me_in),
        onClick = {
            coroutineScope.launch {
                if (emailState.validate(emailValidators) &&
                    passwordState.validate(passwordValidators)
                ) {
                    onSignIn(
                        emailState.inputValue.text.orEmpty(),
                        passwordState.inputValue.text.orEmpty(),
                        rememberMeState
                    )
                }
            }
        }
    )
}
