package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val KairoColorScheme = darkColorScheme(
    primary = KairoNeonCyan,
    onPrimary = KairoVoidBlack,
    primaryContainer = KairoElectricBlue,
    onPrimaryContainer = Color.White,
    secondary = KairoCyberPurple,
    onSecondary = Color.White,
    secondaryContainer = KairoCardElevated,
    onSecondaryContainer = KairoSilverLight,
    tertiary = KairoCyanBright,
    onTertiary = KairoVoidBlack,
    background = KairoVoidBlack,
    onBackground = KairoSilverLight,
    surface = KairoDeepNavy,
    onSurface = KairoSilverLight,
    surfaceVariant = KairoCardDark,
    onSurfaceVariant = KairoSilverMuted,
    outline = KairoBorderGlow,
    outlineVariant = KairoBorderPurple
)

@Composable
fun MyApplicationTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = false, // Preserve strict KAIRO brand palette
    content: @Composable () -> Unit
) {
    MaterialTheme(
        colorScheme = KairoColorScheme,
        typography = Typography,
        content = content
    )
}
