package com.splanes.gifting.ui.feature.wishlists.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.input.categorypicker.GiftCategoryPicker
import com.splanes.gifting.ui.common.components.input.categorypicker.rememberGiftCategoryPickerState
import com.splanes.gifting.ui.common.components.input.text.NotBlank
import com.splanes.gifting.ui.common.components.input.text.TextInput
import com.splanes.gifting.ui.common.components.input.text.TextInputValidator
import com.splanes.gifting.ui.common.components.input.text.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.text.of
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import com.splanes.gifting.ui.theme.GiftingTheme
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistCreateItemForm(
    onCreate: (WishlistItemFormResultData) -> Unit,
    onDismiss: () -> Unit,
    modifier: Modifier = Modifier
) {
    val coroutineScope = rememberCoroutineScope()
    val nameState = rememberTextInputState()
    val priceState = rememberTextInputState()
    val descriptionState = rememberTextInputState()
    val urlState = rememberTextInputState()
    val notesState = rememberTextInputState()
    val categoryPickerState = rememberGiftCategoryPickerState()
    val nameValidators = listOf(
        TextInputValidator.of(
            regex = TextInputValidator.NotBlank,
            feedback = stringResource(id = R.string.field_mandatory)
        )
    )
    val priceValidators = listOf(
        TextInputValidator.of(
            regex = "^\\d+([.,]\\d{1,2})?$".toRegex(),
            feedback = stringResource(id = R.string.field_invalid_price)
        )
    )

    Column(
        modifier = modifier.verticalScroll(state = rememberScrollState())
    ) {
        Spacer(height = 16.dp)

        Text(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = stringResource(id = R.string.wishlist_add_item),
            style = textStyleOf { titleLarge }
        )

        Spacer(height = 16.dp)

        TextInput(
            state = nameState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_name),
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = descriptionState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_description),
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = urlState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_item_url),
                inputType = TextInputVisuals.InputType.Url,
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = priceState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_item_price),
                inputType = TextInputVisuals.InputType.Number,
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        GiftCategoryPicker(state = categoryPickerState)

        Spacer(height = 8.dp)

        TextInput(
            state = notesState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_notes),
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 16.dp)

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            GiftingTextButton(
                text = stringResource(id = R.string.cancel),
                onClick = onDismiss
            )

            Weight()

            GiftingButton(text = stringResource(id = R.string.done)) {
                coroutineScope.launch {
                    val isNameValid = nameState.validate(nameValidators)
                    val isPriceValid =
                        priceState.inputValue.isEmpty || priceState.validate(priceValidators)
                    if (isNameValid && isPriceValid) {
                        val result = WishlistItemFormResultData(
                            name = nameState.inputValue.text.orEmpty(),
                            description = descriptionState.inputValue.text,
                            price = priceState.inputValue.text?.ifBlank { null }?.toDouble(),
                            url = urlState.inputValue.text,
                            categories = categoryPickerState.itemsSelected,
                            notes = notesState.inputValue.text,
                            tags = emptyList() // Todo Tags
                        )
                        onCreate(result)
                    }
                }
            }
        }

        Spacer(height = 24.dp)
    }
}

@Preview("Gift category picker", device = Devices.PIXEL_C)
@Preview("Gift category picker (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Gift category picker (big font)", fontScale = 1.5f)
@Composable
private fun WishlistCreateItemFormPreview() {
    GiftingTheme {
        WishlistCreateItemForm(
            onCreate = { },
            onDismiss = { /*TODO*/ }
        )
    }
}
