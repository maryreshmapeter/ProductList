package com.example.productlist.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.example.productlist.R

private val fonts = FontFamily(
    Font(R.font.poppins_black, FontWeight.Normal),
    Font(R.font.poppins_medium, FontWeight.W500)
)

val appTypography = typographyFromDefaults()

fun typographyFromDefaults(
): Typography {
    val defaults = Typography()
    return Typography(
        displayLarge = defaults.displayLarge.copy(fontFamily = fonts),
        displayMedium = defaults.displayMedium.copy(fontFamily = fonts),
        displaySmall = defaults.displaySmall.copy(fontFamily = fonts),

        headlineLarge = defaults.headlineLarge.copy(fontFamily = fonts),
        headlineMedium = defaults.headlineMedium.copy(fontFamily = fonts),
        headlineSmall = defaults.headlineSmall.copy(fontFamily = fonts),

        titleLarge = defaults.titleLarge.copy(fontFamily = fonts),
        titleMedium = defaults.titleMedium.copy(fontFamily = fonts),
        titleSmall = defaults.titleSmall.copy(fontFamily = fonts),

        bodyLarge = defaults.bodyLarge.copy(fontFamily = fonts),
        bodyMedium = defaults.bodyMedium.copy(fontFamily = fonts),
        bodySmall = defaults.bodySmall.copy(fontFamily = fonts),

        labelLarge = defaults.labelLarge.copy(fontFamily = fonts),
        labelMedium = defaults.labelMedium.copy(fontFamily = fonts),
        labelSmall = defaults.labelSmall.copy(fontFamily = fonts)
    )
}