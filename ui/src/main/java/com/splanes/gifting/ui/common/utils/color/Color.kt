package com.splanes.gifting.ui.common.utils.color

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.compositeOver

@Composable
inline fun colorOf(crossinline block: ColorScheme.() -> Color): Color = MaterialTheme.colorScheme.block()

fun Color.withAlpha(alpha: Double) = copy(alpha = alpha.toFloat())

fun Color.over(other: Color) = compositeOver(other)
