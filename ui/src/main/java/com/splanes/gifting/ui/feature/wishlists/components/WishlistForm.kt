package com.splanes.gifting.ui.feature.wishlists.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material.icons.rounded.ReceiptLong
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
import com.splanes.gifting.ui.common.components.input.text.NotBlank
import com.splanes.gifting.ui.common.components.input.text.TextInput
import com.splanes.gifting.ui.common.components.input.text.TextInputValidator
import com.splanes.gifting.ui.common.components.input.text.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.text.of
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import kotlinx.coroutines.launch

sealed interface OnWishlistFormButtonClick {
    class Create(val onCreate: (NewWishlistRequest) -> Unit) : OnWishlistFormButtonClick

    class Edit(val onEdit: (/* todo */) -> Unit) : OnWishlistFormButtonClick
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistForm(
    initialName: String = "",
    initialDescription: String = "",
    onButtonClick: OnWishlistFormButtonClick,
    onDismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val wishlistNameState = rememberTextInputState(initialName)
    val wishlistDescriptionState = rememberTextInputState(initialDescription)
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
                leadingIcon = Icons.Rounded.ReceiptLong,
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = wishlistDescriptionState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_description),
                leadingIcon = Icons.Rounded.NoteAlt
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
                text = stringResource(
                    id = when (onButtonClick) {
                        is OnWishlistFormButtonClick.Create -> R.string.create
                        is OnWishlistFormButtonClick.Edit -> R.string.edit
                    }
                ),
                onClick = {
                    coroutineScope.launch {
                        val isValid = wishlistNameState.validate(wishlistNameValidators)
                        if (isValid) {
                            when (onButtonClick) {
                                is OnWishlistFormButtonClick.Create -> {
                                    onButtonClick.onCreate(
                                        NewWishlistRequest(
                                            name = wishlistNameState.inputValue.text.orEmpty(),
                                            description = wishlistDescriptionState.inputValue.text
                                        )
                                    )
                                }

                                is OnWishlistFormButtonClick.Edit -> TODO()
                            }
                        }
                    }
                }
            )
        }

        Spacer(height = 16.dp)
    }
}
