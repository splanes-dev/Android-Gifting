package com.splanes.gifting.ui.feature.wishlists.components

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.input.categorypicker.rememberGiftCategoryPickerState
import com.splanes.gifting.ui.common.components.input.text.NotBlank
import com.splanes.gifting.ui.common.components.input.text.TextInputValidator
import com.splanes.gifting.ui.common.components.input.text.of
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.primitives.toPrice
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import com.splanes.gifting.ui.theme.GiftingTheme
import kotlinx.coroutines.launch

@Composable
fun WishlistItemForm(
    modifier: Modifier = Modifier,
    initialName: String = "",
    initialDescription: String = "",
    initialPrice: String = "",
    initialUrl: String = "",
    initialNotes: String = "",
    initialCategories: List<GiftCategory> = emptyList(),
    onButtonClick: (WishlistItemFormResultData) -> Unit,
    onDismiss: () -> Unit
) {
    val coroutineScope = rememberCoroutineScope()
    val nameState = rememberTextInputState(initialName)
    val priceState = rememberTextInputState(initialPrice)
    val descriptionState = rememberTextInputState(initialDescription)
    val urlState = rememberTextInputState(initialUrl)
    val notesState = rememberTextInputState(initialNotes)
    val categoryPickerState = rememberGiftCategoryPickerState(initialCategories)
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

        WishlistItemFields(
            nameState = nameState,
            descriptionState = descriptionState,
            priceState = priceState,
            urlState = urlState,
            categoryPickerState = categoryPickerState,
            notesState = notesState
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
                            price = priceState.inputValue.text?.toPrice(),
                            url = urlState.inputValue.text,
                            categories = categoryPickerState.itemsSelected,
                            notes = notesState.inputValue.text,
                            tags = emptyList() // Todo Tags
                        )
                        onButtonClick(result)
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
        WishlistItemForm(
            onButtonClick = { },
            onDismiss = { }
        )
    }
}
