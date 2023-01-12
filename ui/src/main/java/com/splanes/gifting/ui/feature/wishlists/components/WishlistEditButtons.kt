package com.splanes.gifting.ui.feature.wishlists.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Row
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.DeleteForever
import androidx.compose.material.icons.rounded.EditNote
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.R
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.spacer.row.Spacer

@Composable
fun WishlistEditButtons(
    editable: Boolean,
    modifier: Modifier = Modifier,
    onEdit: () -> Unit,
    onDelete: () -> Unit
) {
    Row(modifier = modifier) {
        AnimatedVisibility(visible = editable) {
            GiftingButton(
                text = stringResource(id = R.string.edit),
                leadingIcon = Icons.Rounded.EditNote,
                onClick = onEdit
            )
        }

        if (editable) Spacer(width = 16.dp)

        GiftingButton(
            text = stringResource(id = R.string.delete),
            leadingIcon = Icons.Rounded.DeleteForever,
            onClick = onDelete
        )
    }
}
