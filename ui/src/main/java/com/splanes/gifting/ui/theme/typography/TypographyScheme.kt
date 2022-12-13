package com.splanes.gifting.ui.theme.typography

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.FontWeight

val TypographyScheme: Typography by lazy {
    Typography(
        displayLarge = TypographyParamsTokens.DisplayLarge.toTextStyle(
            family = FontFamilyTokens.Dosis,
            weight = FontWeight.Light
        ),
        displayMedium = TypographyParamsTokens.DisplayMedium.toTextStyle(
            family = FontFamilyTokens.Dosis,
            weight = FontWeight.Light
        ),
        displaySmall = TypographyParamsTokens.DisplaySmall.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        headlineLarge = TypographyParamsTokens.HeadlineLarge.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        headlineMedium = TypographyParamsTokens.HeadlineMedium.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        headlineSmall = TypographyParamsTokens.HeadlineSmall.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        titleLarge = TypographyParamsTokens.TitleLarge.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        titleMedium = TypographyParamsTokens.TitleMedium.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        titleSmall = TypographyParamsTokens.TitleSmall.toTextStyle(
            family = FontFamilyTokens.Josefin
        ),
        bodyLarge = TypographyParamsTokens.BodyLarge.toTextStyle(
            family = FontFamilyTokens.Barlow
        ),
        bodyMedium = TypographyParamsTokens.BodyMedium.toTextStyle(
            family = FontFamilyTokens.Barlow
        ),
        bodySmall = TypographyParamsTokens.BodySmall.toTextStyle(
            family = FontFamilyTokens.Barlow
        ),
        labelLarge = TypographyParamsTokens.LabelLarge.toTextStyle(
            family = FontFamilyTokens.Dosis
        ),
        labelMedium = TypographyParamsTokens.LabelMedium.toTextStyle(
            family = FontFamilyTokens.Dosis
        ),
        labelSmall = TypographyParamsTokens.LabelSmall.toTextStyle(
            family = FontFamilyTokens.Dosis
        )
    )
}
