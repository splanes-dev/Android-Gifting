package com.splanes.gifting.ui.theme.color

import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color

private val primaryLight = Color(0xFF006C47)
private val onPrimaryLight = Color(0xFFFFFFFF)
private val primaryContainerLight = Color(0xFF8EF7C1)
private val onPrimaryContainerLight = Color(0xFF002113)
private val secondaryLight = Color(0xFF855400)
private val onSecondaryLight = Color(0xFFFFFFFF)
private val secondaryContainerLight = Color(0xFFFFDDB7)
private val onSecondaryContainerLight = Color(0xFF2A1700)
private val tertiaryLight = Color(0xFF3C6472)
private val onTertiaryLight = Color(0xFFFFFFFF)
private val tertiaryContainerLight = Color(0xFFC0E9FA)
private val onTertiaryContainerLight = Color(0xFF001F28)
private val errorLight = Color(0xFFBA1A1A)
private val errorContainerLight = Color(0xFFFFDAD6)
private val onErrorLight = Color(0xFFFFFFFF)
private val onErrorContainerLight = Color(0xFF410002)
private val backgroundLight = Color(0xFFFBFDF8)
private val onBackgroundLight = Color(0xFF191C1A)
private val surfaceLight = Color(0xFFFBFDF8)
private val onSurfaceLight = Color(0xFF191C1A)
private val surfaceVariantLight = Color(0xFFDCE5DC)
private val onSurfaceVariantLight = Color(0xFF404943)
private val outlineLight = Color(0xFF707972)
private val inverseOnSurfaceLight = Color(0xFFEFF1ED)
private val inverseSurfaceLight = Color(0xFF2E312E)
private val inversePrimaryLight = Color(0xFF71DAA6)
private val surfaceTintLight = Color(0xFF006C47)
private val outlineVariantLight = Color(0xFFC0C9C1)
private val scrimLight = Color(0xFF000000)

internal val LightColorScheme by lazy {
    lightColorScheme(
        primary = primaryLight,
        onPrimary = onPrimaryLight,
        primaryContainer = primaryContainerLight,
        onPrimaryContainer = onPrimaryContainerLight,
        secondary = secondaryLight,
        onSecondary = onSecondaryLight,
        secondaryContainer = secondaryContainerLight,
        onSecondaryContainer = onSecondaryContainerLight,
        tertiary = tertiaryLight,
        onTertiary = onTertiaryLight,
        tertiaryContainer = tertiaryContainerLight,
        onTertiaryContainer = onTertiaryContainerLight,
        background = backgroundLight,
        onBackground = onBackgroundLight,
        surface = surfaceLight,
        onSurface = onSurfaceLight,
        surfaceTint = surfaceTintLight,
        surfaceVariant = surfaceVariantLight,
        onSurfaceVariant = onSurfaceVariantLight,
        error = errorLight,
        onError = onErrorLight,
        errorContainer = errorContainerLight,
        onErrorContainer = onErrorContainerLight,
        outline = outlineLight,
        outlineVariant = outlineVariantLight,
        scrim = scrimLight,
        inversePrimary = inversePrimaryLight,
        inverseSurface = inverseSurfaceLight,
        inverseOnSurface = inverseOnSurfaceLight
    )
}
