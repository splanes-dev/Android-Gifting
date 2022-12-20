package com.splanes.gifting.ui.common.components.buttons

import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.common.components.spacer.row.Spacer
import com.splanes.gifting.ui.common.utils.typography.textStyleOf

@Composable
internal fun RowScope.ButtonContent(
    text: String,
    leadingIcon: ImageVector?,
    trailingIcon: ImageVector?
) {
    leadingIcon?.let {
        Spacer(width = 8.dp)
        Icon(
            imageVector = leadingIcon,
            contentDescription = text
        )
        Spacer(width = 12.dp)
    }
    Text(
        text = text,
        style = textStyleOf { titleMedium },
        maxLines = 1
    )
    trailingIcon?.let {
        Spacer(width = 12.dp)
        Icon(
            imageVector = trailingIcon,
            contentDescription = text
        )
        Spacer(width = 8.dp)
    }
}
