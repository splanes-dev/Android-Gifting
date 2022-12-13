package com.splanes.gifting.ui.theme.typography

import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp

data class TextStyleParams(
    val fontWeight: FontWeight = FontWeight.Normal,
    val fontSize: Int,
    val lineHeight: Int,
    val letterSpacing: Double
)

fun TextStyleParams.toTextStyle(
    family: FontFamily,
    weight: FontWeight = fontWeight,
    size: TextUnit = fontSize.sp,
    height: TextUnit = lineHeight.sp,
    spacing: TextUnit = letterSpacing.sp
) = TextStyle(
    fontFamily = family,
    fontWeight = weight,
    letterSpacing = spacing,
    lineHeight = height,
    fontSize = size
)
