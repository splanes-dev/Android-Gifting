package com.splanes.gifting.ui.common.utils.typography

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.TextStyle

@Composable
inline fun textStyleOf(crossinline block: Typography.() -> TextStyle) =
    MaterialTheme.typography.block()
