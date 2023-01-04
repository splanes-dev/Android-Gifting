package com.splanes.gifting.ui.common.utils.modifier

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.splanes.gifting.ui.common.components.spacer.column.Spacer
import com.splanes.gifting.ui.common.utils.color.colorOf
import com.splanes.gifting.ui.theme.GiftingTheme

fun Modifier.onClick(onClick: () -> Unit) = composed {
    then(
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = null,
            onClick = onClick
        )
    )
}

fun Modifier.onRippleClick(color: Color, onClick: () -> Unit) = composed {
    then(
        Modifier.clickable(
            interactionSource = remember { MutableInteractionSource() },
            indication = rememberRipple(color = color),
            onClick = onClick
        )
    )
}

@Preview
@Composable
private fun ModifierOnRippleClickPreview() {
    GiftingTheme {
        val context = LocalContext.current
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                modifier = Modifier.onRippleClick(
                    color = colorOf { primary },
                    onClick = {
                        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                    }
                ),
                text = "With Ripple primary"
            )
            Spacer(height = 16.dp)
            Text(
                modifier = Modifier.onRippleClick(
                    color = colorOf { tertiary },
                    onClick = {
                        Toast.makeText(context, "Clicked", Toast.LENGTH_SHORT).show()
                    }
                ),
                text = "With Ripple tertiary"
            )
        }
    }
}
