package com.splanes.gifting.ui.common.components.buttons

import android.content.res.Configuration
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.theme.GiftingTheme

@Composable
fun GiftingTextButton(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.textButtonColors(),
    onClick: () -> Unit
) {
    TextButton(
        modifier = modifier,
        onClick = onClick,
        colors = colors
    ) {
        ButtonContent(text = text, leadingIcon = leadingIcon, trailingIcon = trailingIcon)
    }
}

@Composable
fun GiftingButton(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.buttonColors(),
    onClick: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = onClick,
        colors = colors
    ) {
        ButtonContent(text = text, leadingIcon = leadingIcon, trailingIcon = trailingIcon)
    }
}

@Composable
fun GiftingOutlinedButton(
    text: String,
    modifier: Modifier = Modifier,
    leadingIcon: ImageVector? = null,
    trailingIcon: ImageVector? = null,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(),
    onClick: () -> Unit
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
        colors = colors
    ) {
        ButtonContent(text = text, leadingIcon = leadingIcon, trailingIcon = trailingIcon)
    }
}

@Composable
fun GiftingIconButton(
    imageVector: ImageVector,
    modifier: Modifier = Modifier,
    size: Dp = 24.dp,
    description: String? = null,
    tint: Color = Color.Unspecified,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        onClick = onClick
    ) {
        Icon(
            modifier = Modifier.size(size),
            imageVector = imageVector,
            tint = tint,
            contentDescription = description
        )
    }
}

@Preview("Text button", device = Devices.PIXEL_C)
@Preview("Text button (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Text button (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
private fun PreviewGiftingTextButton() {
    GiftingTheme {
        GiftingTextButton(
            text = "Button",
            onClick = {}
        )
    }
}

@Preview("Button", device = Devices.PIXEL_C)
@Preview("Button (dark)", uiMode = Configuration.UI_MODE_NIGHT_YES, device = Devices.PIXEL_C)
@Preview("Button (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
private fun PreviewGiftingButton() {
    GiftingTheme {
        GiftingButton(
            text = "Button",
            onClick = {}
        )
    }
}

@Preview("Outlined button", device = Devices.PIXEL_C)
@Preview(
    "Outlined button (dark)",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_C
)
@Preview("Outlined button (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
private fun PreviewGiftingOutlinedButton() {
    GiftingTheme {
        GiftingOutlinedButton(
            text = "Button",
            onClick = {}
        )
    }
}

@Preview("Icon button", device = Devices.PIXEL_C)
@Preview(
    "Icon button (dark)",
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    device = Devices.PIXEL_C
)
@Preview("Icon button (big font)", fontScale = 1.5f, device = Devices.PIXEL_C)
@Composable
private fun PreviewGiftingIconButton() {
    GiftingTheme {
        GiftingIconButton(
            imageVector = Icons.Default.Edit,
            onClick = {}
        )
    }
}
