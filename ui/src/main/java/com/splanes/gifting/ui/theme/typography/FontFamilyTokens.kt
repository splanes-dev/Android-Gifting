package com.splanes.gifting.ui.theme.typography

import androidx.annotation.FontRes
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontLoadingStrategy
import androidx.compose.ui.text.font.FontWeight
import com.splanes.gifting.ui.R.font as RFont

object FontFamilyTokens {
    val Barlow: FontFamily by lazy {
        FontFamily(
            fontOptionLocalOf(RFont.barlow_bold, weight = FontWeight.Bold),
            fontOptionLocalOf(RFont.barlow_semi_bold, weight = FontWeight.SemiBold),
            fontOptionLocalOf(RFont.barlow_medium, weight = FontWeight.Medium),
            fontOptionLocalOf(RFont.barlow_regular),
            fontOptionLocalOf(RFont.barlow_light, weight = FontWeight.Light),
            fontOptionLocalOf(RFont.barlow_thin, weight = FontWeight.Thin)
        )
    }
    val Dosis: FontFamily by lazy {
        FontFamily(
            fontOptionLocalOf(RFont.dosis_bold, weight = FontWeight.Bold),
            fontOptionLocalOf(RFont.dosis_semi_bold, weight = FontWeight.SemiBold),
            fontOptionLocalOf(RFont.dosis_medium, weight = FontWeight.Medium),
            fontOptionLocalOf(RFont.dosis_regular),
            fontOptionLocalOf(RFont.dosis_light, weight = FontWeight.Light),
            fontOptionLocalOf(RFont.dosis_extra_light, weight = FontWeight.Thin)
        )
    }
    val Josefin: FontFamily by lazy {
        FontFamily(
            fontOptionLocalOf(RFont.josefin_sans_bold, weight = FontWeight.Bold),
            fontOptionLocalOf(RFont.josefin_sans_semi_bold, weight = FontWeight.SemiBold),
            fontOptionLocalOf(RFont.josefin_sans_medium, weight = FontWeight.Medium),
            fontOptionLocalOf(RFont.josefin_sans_regular),
            fontOptionLocalOf(RFont.josefin_sans_light, weight = FontWeight.Light),
            fontOptionLocalOf(RFont.josefin_sans_thin, weight = FontWeight.Thin)
        )
    }
}

private fun fontOptionLocalOf(@FontRes id: Int, weight: FontWeight = FontWeight.Normal) =
    Font(id, weight = weight, loadingStrategy = FontLoadingStrategy.OptionalLocal)
