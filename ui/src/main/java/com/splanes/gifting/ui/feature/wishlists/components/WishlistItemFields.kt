package com.splanes.gifting.ui.feature.wishlists.components

import androidx.compose.foundation.layout.Column
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Euro
import androidx.compose.material.icons.rounded.NoteAlt
import androidx.compose.material.icons.rounded.Notes
import androidx.compose.material.icons.rounded.Sell
import androidx.compose.material.icons.rounded.TravelExplore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.input.categorypicker.GiftCategoryPicker
import com.splanes.gifting.ui.common.components.input.categorypicker.GiftCategoryPickerState
import com.splanes.gifting.ui.common.components.input.categorypicker.rememberGiftCategoryPickerState
import com.splanes.gifting.ui.common.components.input.text.TextInput
import com.splanes.gifting.ui.common.components.input.text.TextInputState
import com.splanes.gifting.ui.common.components.input.text.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WishlistItemFields(
    modifier: Modifier = Modifier,
    nameState: TextInputState = rememberTextInputState(),
    descriptionState: TextInputState = rememberTextInputState(),
    priceState: TextInputState = rememberTextInputState(),
    urlState: TextInputState = rememberTextInputState(),
    categoryPickerState: GiftCategoryPickerState = rememberGiftCategoryPickerState(),
    notesState: TextInputState = rememberTextInputState()
) {
    Column(modifier = modifier) {
        TextInput(
            state = nameState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_name),
                leadingIcon = Icons.Rounded.Sell,
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = descriptionState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_description),
                leadingIcon = Icons.Rounded.NoteAlt,
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = urlState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_item_url),
                inputType = TextInputVisuals.InputType.Url,
                leadingIcon = Icons.Rounded.TravelExplore,
                imeAction = ImeAction.Next
            )
        )

        Spacer(height = 8.dp)

        TextInput(
            state = priceState,
            visuals = TextInputVisuals(
                label = stringResource(id = R.string.wishlist_item_price),
                inputType = TextInputVisuals.InputType.Number,
                leadingIcon = Icons.Rounded.Euro,
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
                leadingIcon = Icons.Rounded.Notes,
                imeAction = ImeAction.Next
            )
        )
    }
}
