package com.splanes.gifting.ui.feature.wishlists.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.wishlist.request.NewWishlistRequest
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.input.NotBlank
import com.splanes.gifting.ui.common.components.input.TextInput
import com.splanes.gifting.ui.common.components.input.TextInputValidator
import com.splanes.gifting.ui.common.components.input.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.of
import com.splanes.gifting.ui.common.components.input.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistCreateForm(
    onCreate: (NewWishlistRequest) -> Unit,
    onDismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val wishlistNameState = rememberTextInputState()
    val wishlistDescriptionState = rememberTextInputState()
    val wishlistNameValidators = listOf(
        TextInputValidator.of(
            regex = TextInputValidator.NotBlank,
            feedback = stringResource(id = R.string.field_mandatory)
        )
    )

    Column(
        modifier = Modifier.padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(id = R.string.wishlist_create),
            style = textStyleOf { titleLarge }
        )

        Spacer(height = 16.dp)

        TextInput(
            state = wishlistNameState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_name),
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = wishlistDescriptionState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_description)
            )
        )

        Spacer(height = 24.dp)

        Row(modifier = Modifier.padding(horizontal = 12.dp)) {
            GiftingTextButton(
                text = stringResource(id = R.string.cancel),
                colors = ButtonDefaults.textButtonColors(contentColor = colorOf { error }),
                onClick = onDismiss
            )

            Weight()

            GiftingButton(
                text = stringResource(id = R.string.create),
                onClick = {
                    coroutineScope.launch {
                        val isValid = wishlistNameState.validate(wishlistNameValidators)
                        if (isValid) {
                            onCreate(
                                NewWishlistRequest(
                                    name = wishlistNameState.inputValue.text.orEmpty(),
                                    description = wishlistDescriptionState.inputValue.text
                                )
                            )
                        }
                    }
                }
            )
        }

        Spacer(height = 16.dp)
    }
}
