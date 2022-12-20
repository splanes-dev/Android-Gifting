package com.splanes.gifting.ui.feature.authentication.model

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.ColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

data class OnBoardingUiPage(
    @DrawableRes val image: Int,
    @StringRes val title: Int,
    @StringRes val description: Int,
    val background: @Composable ColorScheme.() -> Color
)
