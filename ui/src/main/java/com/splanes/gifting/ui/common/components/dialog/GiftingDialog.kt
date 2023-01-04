package com.splanes.gifting.ui.common.components.dialog

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.NewReleases
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.DialogProperties
import com.splanes.gifting.ui.common.components.buttons.GiftingButton
import com.splanes.gifting.ui.common.components.buttons.GiftingTextButton
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.common.utils.typography.textStyleOf
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun GiftingDialog(
    dialogState: DialogState,
    confirmButton: DialogButtonUi,
    modifier: Modifier = Modifier,
    title: String? = null,
    icon: DialogIconUi? = null,
    dismissButton: DialogButtonUi? = null,
    properties: DialogProperties = DialogProperties(),
    content: @Composable () -> Unit
) {
    AnimatedVisibility(
        visible = dialogState.isVisible,
        enter = fadeIn(animationSpec = tween(durationMillis = 300)),
        exit = fadeOut(animationSpec = tween(durationMillis = 300))
    ) {
        AlertDialog(
            modifier = modifier,
            properties = properties,
            onDismissRequest = { dialogState.hide() },
            confirmButton = {
                GiftingButton(
                    text = confirmButton.text,
                    onClick = {
                        confirmButton.onClick()
                        dialogState.hide()
                    }
                )
            },
            dismissButton = dismissButton?.let {
                {
                    GiftingTextButton(
                        text = dismissButton.text,
                        onClick = {
                            dismissButton.onClick()
                            dialogState.hide()
                        }
                    )
                }
            },
            icon = icon?.let {
                {
                    Icon(
                        modifier = Modifier.size(24.dp),
                        imageVector = icon.icon,
                        contentDescription = title,
                        tint = icon.color
                    )
                }
            },
            title = title?.let {
                {
                    Text(
                        text = title,
                        style = textStyleOf { headlineSmall },
                        color = colorOf { primary }
                    )
                }
            },
            text = { content() }
        )
    }
}

@Preview("Dialog", device = Devices.PIXEL_C)
@Preview("Dialog (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Dialog (big font)", fontScale = 1.5f)
@Composable
private fun GiftingDialogPreview() {
    GiftingTheme {
        val dialogState = rememberDialogState()
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GiftingButton(text = "Show") {
                dialogState.show()
            }

            GiftingDialog(
                dialogState = dialogState,
                confirmButton = DialogButtonUi(
                    text = "Ok",
                    onClick = {}
                ),
                dismissButton = DialogButtonUi(
                    text = "Cancel",
                    onClick = {}
                ),
                title = "Gifting dialog",
                icon = DialogIconUi(
                    icon = Icons.Rounded.NewReleases,
                    color = colorOf { primary }
                )
            ) {
                Text(text = "Hey there is a new dialog here :D")
            }
        }
    }
}

@Preview("Dialog", device = Devices.PIXEL_C)
@Preview("Dialog (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES)
@Preview("Dialog (big font)", fontScale = 1.5f)
@Composable
private fun GiftingDialogPreview2() {
    GiftingTheme {
        val dialogState = rememberDialogState()
        val items = listOf(
            "Item 1",
            "Item 2",
            "Item 3",
            "Item 4",
            "Item 5",
            "Item 6",
            "Item 7",
            "Item 8",
            "Item 9",
            "Item 10"
        )
        val selected = remember { mutableStateListOf<String>() }
        Box(
            modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            GiftingButton(text = "Show") {
                dialogState.show()
            }

            GiftingDialog(
                dialogState = dialogState,
                confirmButton = DialogButtonUi(
                    text = "Ok",
                    onClick = {}
                ),
                title = "Gifting dialog"
            ) {
                LazyColumn {
                    items(items) { item ->
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(vertical = 8.dp)
                        ) {
                            Checkbox(
                                checked = selected.contains(item),
                                onCheckedChange = { checked ->
                                    if (checked) {
                                        selected.add(item)
                                    } else {
                                        selected.remove(item)
                                    }
                                }
                            )
                            Spacer(width = 16.dp)
                            Text(text = item)
                        }
                    }
                }
            }
        }
    }
}
