package com.splanes.gifting.ui.feature.wishlists.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.wishlist.model.WishlistItem
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.input.categorypicker.rememberGiftCategoryPickerState
import com.splanes.gifting.ui.common.components.input.text.NotBlank
import com.splanes.gifting.ui.common.components.input.text.TextInputValidator
import com.splanes.gifting.ui.common.components.input.text.of
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.utils.primitives.toPrice
import com.splanes.gifting.ui.feature.wishlists.model.WishlistItemFormResultData
import kotlinx.coroutines.launch

@Composable
fun WishlistItemEditForm(
    item: WishlistItem,
    modifier: Modifier = Modifier,
    onButtonClick: (WishlistItemFormResultData) -> Unit
) {
    val nameState = rememberTextInputState(item.name)
    val descriptionState = rememberTextInputState(item.description.orEmpty())
    val priceState = rememberTextInputState(item.price?.toString().orEmpty())
    val urlState = rememberTextInputState(item.url.orEmpty())
    val categoryState = rememberGiftCategoryPickerState(item.categories)
    val notesState = rememberTextInputState(item.notes.orEmpty())
    val textStates = listOf(
        nameState,
        descriptionState,
        priceState,
        urlState,
        notesState
    )
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
    val coroutineScope = rememberCoroutineScope()

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        WishlistItemFields(
            nameState = nameState,
            descriptionState = descriptionState,
            priceState = priceState,
            urlState = urlState,
            categoryPickerState = categoryState,
            notesState = notesState
        )

        Spacer(height = 16.dp)

        GiftingButton(
            text = stringResource(id = R.string.edit),
            enabled = textStates.any { it.hasChanged } || categoryState.hasChanged
        ) {
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
                        categories = categoryState.itemsSelected,
                        notes = notesState.inputValue.text,
                        tags = emptyList() // Todo Tags
                    )
                    onButtonClick(result)
                }
            }
        }

        Spacer(height = 16.dp)
    }
}
