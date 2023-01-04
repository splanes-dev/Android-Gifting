package com.splanes.gifting.ui.common.components.input.categorypicker

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AddCircle
import androidx.compose.material.icons.rounded.Check
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.domain.feature.list.model.GiftCategory
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.dialog.DialogButtonUi
import com.splanes.gifting.ui.common.components.dialog.GiftingDialog
import com.splanes.gifting.ui.common.components.dialog.rememberDialogState
import com.splanes.gifting.ui.common.components.input.text.TextInput
import com.splanes.gifting.ui.common.components.input.text.TextInputVisuals
import com.splanes.gifting.ui.common.components.input.text.rememberTextInputState
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.components.spacer.row.Weight
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.color.withAlpha
import com.splanes.gifting.ui.common.utils.giftcategory.icon
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GiftCategoryPicker(
    state: GiftCategoryPickerState,
    modifier: Modifier = Modifier
) {
    val dialogState = rememberDialogState()
    Box() {
        Surface(
            modifier = modifier.padding(top = 10.dp),
            color = Color.Transparent,
            shape = RoundedCornerShape(4.dp),
            border = BorderStroke(
                width = 1.dp,
                color = colorOf { onSurface.withAlpha(.7) }
            ),
            onClick = { dialogState.show() }
        ) {
            Column(
                modifier = Modifier
                    .padding(
                        start = 16.dp,
                        end = 16.dp,
                        top = 16.dp,
                        bottom = 8.dp
                    )
            ) {
                state.itemsSelected.forEach { category ->
                    GiftCategoryPickerItem(category = category)
                }

                GiftCategoryPickerEmpty(visible = state.itemsSelected.isEmpty())
            }
        }

        AnimatedVisibility(
            modifier = Modifier
                .padding(start = 12.dp)
                .align(Alignment.TopStart),
            visible = state.itemsSelected.isNotEmpty(),
            enter = fadeIn(animationSpec = tween(300)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Text(
                modifier = Modifier
                    .background(color = colorOf { background })
                    .padding(horizontal = 4.dp),
                text = stringResource(id = R.string.category_picker_title),
                style = textStyleOf { bodyMedium }
            )
        }
    }

    GiftingDialog(
        dialogState = dialogState,
        confirmButton = DialogButtonUi(
            text = stringResource(id = R.string.done),
            onClick = {}
        ),
        title = stringResource(id = R.string.category_picker_title)
    ) {
        GiftCategoryPickerModal(state)
    }
}

@Composable
private fun GiftCategoryPickerItem(category: GiftCategory) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = category.icon(),
                contentDescription = category.name,
                tint = colorOf { onSurface.withAlpha(.5) }
            )

            Spacer(width = 12.dp)

            Text(
                text = category.name,
                style = textStyleOf { titleMedium },
                color = colorOf { onSurface.withAlpha(.5) }
            )
        }

        Spacer(height = 8.dp)
    }
}

@Composable
private fun GiftCategoryPickerEmpty(visible: Boolean) {
    AnimatedVisibility(
        visible = visible,
        enter = fadeIn(animationSpec = tween(durationMillis = 500)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 8.dp)
        ) {
            Icon(
                imageVector = Icons.Rounded.AddCircle,
                contentDescription = null,
                tint = colorOf { primary }
            )

            Spacer(width = 16.dp)

            Text(
                text = stringResource(id = R.string.category_picker_add_item),
                style = textStyleOf { bodyLarge },
                color = colorOf { onSurface.withAlpha(.7) }
            )

            Weight()
        }
    }
}

@Composable
private fun GiftCategoryPickerModal(state: GiftCategoryPickerState) {
    Column {
        Spacer(height = 8.dp)

        LazyColumn {
            items(GiftCategory.values()) { category ->
                val isSelected = state.isSelected(category)
                TextButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    onClick = { state.pick(category) }
                ) {
                    Text(
                        text = category.name,
                        style = textStyleOf { bodyLarge },
                        color = colorOf {
                            if (isSelected) {
                                primary
                            } else {
                                onSurface
                            }
                        }
                    )

                    Weight()

                    AnimatedVisibility(visible = isSelected) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            imageVector = Icons.Rounded.Check,
                            contentDescription = null,
                            tint = colorOf { primary }
                        )
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview("Gift category picker", device = Devices.PIXEL_C)
@Preview("Gift category picker (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Gift category picker (big font)", fontScale = 1.5f)
@Composable
private fun GiftCategoryPickerPreview() {
    GiftingTheme {
        Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
            val priceState = rememberTextInputState()
            TextInput(
                state = priceState,
                visuals = TextInputVisuals(
                    label = stringResource(id = R.string.wishlist_description)
                )
            )
            GiftCategoryPicker(state = rememberGiftCategoryPickerState())
        }
    }
}
