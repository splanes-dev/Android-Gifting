package com.splanes.gifting.ui.theme.color

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color

private val primaryDark = Color(0xFF71DAA6)
private val onPrimaryDark = Color(0xFF003823)
private val primaryContainerDark = Color(0xFF005234)
private val onPrimaryContainerDark = Color(0xFF8EF7C1)
private val secondaryDark = Color(0xFFFFB95D)
private val onSecondaryDark = Color(0xFF462A00)
private val secondaryContainerDark = Color(0xFF653E00)
private val onSecondaryContainerDark = Color(0xFFFFDDB7)
private val tertiaryDark = Color(0xFFA4CDDD)
private val onTertiaryDark = Color(0xFF053542)
private val tertiaryContainerDark = Color(0xFF234C5A)
private val onTertiaryContainerDark = Color(0xFFC0E9FA)
private val errorDark = Color(0xFFFFB4AB)
private val errorContainerDark = Color(0xFF93000A)
private val onErrorDark = Color(0xFF690005)
private val onErrorContainerDark = Color(0xFFFFDAD6)
private val backgroundDark = Color(0xFF191C1A)
private val onBackgroundDark = Color(0xFFE1E3DF)
private val surfaceDark = Color(0xFF191C1A)
private val onSurfaceDark = Color(0xFFE1E3DF)
private val surfaceVariantDark = Color(0xFF404943)
private val onSurfaceVariantDark = Color(0xFFC0C9C1)
private val outlineDark = Color(0xFF8A938C)
private val inverseOnSurfaceDark = Color(0xFF191C1A)
private val inverseSurfaceDark = Color(0xFFE1E3DF)
private val inversePrimaryDark = Color(0xFF006C47)
private val surfaceTintDark = Color(0xFF71DAA6)
private val outlineVariantDark = Color(0xFF404943)
private val scrimDark = Color(0xFF000000)

internal val DarkColorScheme by lazy {
    darkColorScheme(
        primary = primaryDark,
        onPrimary = onPrimaryDark,
        primaryContainer = primaryContainerDark,
        onPrimaryContainer = onPrimaryContainerDark,
        secondary = secondaryDark,
        onSecondary = onSecondaryDark,
        secondaryContainer = secondaryContainerDark,
        onSecondaryContainer = onSecondaryContainerDark,
        tertiary = tertiaryDark,
        onTertiary = onTertiaryDark,
        tertiaryContainer = tertiaryContainerDark,
        onTertiaryContainer = onTertiaryContainerDark,
        background = backgroundDark,
        onBackground = onBackgroundDark,
        surface = surfaceDark,
        onSurface = onSurfaceDark,
        surfaceTint = surfaceTintDark,
        surfaceVariant = surfaceVariantDark,
        onSurfaceVariant = onSurfaceVariantDark,
        error = errorDark,
        onError = onErrorDark,
        errorContainer = errorContainerDark,
        onErrorContainer = onErrorContainerDark,
        outline = outlineDark,
        outlineVariant = outlineVariantDark,
        scrim = scrimDark,
        inversePrimary = inversePrimaryDark,
        inverseSurface = inverseSurfaceDark,
        inverseOnSurface = inverseOnSurfaceDark
    )
}
