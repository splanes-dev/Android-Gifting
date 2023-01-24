package com.splanes.gifting.ui.feature.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Mail
import androidx.compose.material.icons.rounded.Password
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.splanes.gifting.domain.feature.profile.model.Profile
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.input.text.NotBlank
import com.splanes.gifting.ui.common.components.input.text.Password
import com.splanes.gifting.ui.common.components.input.text.TextInput
import com.splanes.gifting.ui.common.components.input.text.TextInputValidator
import com.splanes.gifting.ui.common.components.input.text.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.text.of
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.feature.profile.model.ProfileBasicInfoEditFormData
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileBasicInfoEditForm(
    profile: Profile,
    onEdit: (ProfileBasicInfoEditFormData) -> Unit,
    onCancel: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val scrollState = rememberScrollState()
    val emailState = rememberTextInputState(profile.email)
    val usernameState = rememberTextInputState(profile.name)
    val passwordState = rememberTextInputState()
    val avatarPainter = rememberAsyncImagePainter(model = profile.avatarUrl)
    val usernameValidators = listOf(
        TextInputValidator.of(
            regex = TextInputValidator.NotBlank,
            feedback = stringResource(id = R.string.field_mandatory)
        )
    )
    val passwordValidator = listOf(
        TextInputValidator.of(
            regex = TextInputValidator.Password,
            feedback = stringResource(id = R.string.field_password_weak)
        )
    )

    Column(
        modifier = modifier.verticalScroll(scrollState),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            modifier = Modifier
                .size(75.dp)
                .clip(RoundedCornerShape(8.dp)),
            painter = avatarPainter,
            contentDescription = profile.name
        )

        Spacer(height = 16.dp)

        TextInput(
            state = emailState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.email),
                leadingIcon = Icons.Rounded.Mail,
                imeAction = ImeAction.Next
            ),
            enabled = false
        )

        Spacer(height = 8.dp)

        TextInput(
            state = usernameState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.username),
                leadingIcon = Icons.Rounded.AccountCircle,
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

        Spacer(height = 32.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GiftingTextButton(
                text = stringResource(id = R.string.cancel),
                onClick = onCancel
            )

            Weight()

            GiftingButton(
                text = stringResource(id = R.string.done),
                enabled = usernameState.hasChanged || !passwordState.inputValue.isEmpty
            ) {
                coroutineScope.launch {
                    val usernameValid = usernameState.validate(usernameValidators)
                    val passwordValid = passwordState.inputValue.isEmpty ||
                        passwordState.validate(passwordValidator)

                    if (usernameValid && passwordValid) {
                        val form = ProfileBasicInfoEditFormData(
                            username = usernameState.inputValue.text.orEmpty(),
                            password = passwordState.inputValue.text?.ifBlank { null }
                        )
                        onEdit(form)
                    }
                }
            }
        }
    }
}
