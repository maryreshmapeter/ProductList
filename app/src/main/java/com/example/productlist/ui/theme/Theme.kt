package com.example.productlist.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.Gray
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.graphics.Color.Companion.Yellow

private val DarkColorPalette = darkColorScheme(
    primary = White,
    secondary = Yellow,
    surface = White,
    background = LightGray,
    onSurface = White,
    onBackground = White,
)

private val LightColorPalette = lightColorScheme(
    primary = Black,
    secondary = Yellow,
    onPrimary = White,
    onSecondary = Black,
    background = LightGray,
    surface = White,
    onSurface = Gray
)

@Composable
fun ProductListTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }
    MaterialTheme(
        colorScheme = colors,
        typography = appTypography,
        content = content
    )

}