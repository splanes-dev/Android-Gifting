package com.splanes.gifting.ui.feature.authentication.components

import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
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
import com.splanes.gifting.ui.common.components.input.text.Email
import com.splanes.gifting.ui.common.components.input.text.NotBlank
import com.splanes.gifting.ui.common.components.input.text.Password
import com.splanes.gifting.ui.common.components.input.text.TextInput
import com.splanes.gifting.ui.common.components.input.text.TextInputValidator
import com.splanes.gifting.ui.common.components.input.text.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.text.of
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.column.Weight
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.remember.rememberStateOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.authentication.model.SignUpFormData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun ColumnScope.SignUpForm(
    username: String,
    email: String,
    password: String,
    autoSignIn: Boolean,
    onSignUp: (SignUpFormData) -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val usernameState = rememberTextInputState(username)
    val emailState = rememberTextInputState(email)
    val passwordState = rememberTextInputState(password)
    var rememberMeState by rememberStateOf(value = autoSignIn)
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
                    val form = SignUpFormData(
                        username = usernameState.inputValue.text.orEmpty(),
                        email = emailState.inputValue.text.orEmpty(),
                        password = passwordState.inputValue.text.orEmpty(),
                        autoSignIn = rememberMeState
                    )
                    onSignUp(form)
                }
            }
        }
    )
}
