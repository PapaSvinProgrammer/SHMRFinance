package com.example.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun SHMRFinanceTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    currentColor: Color = primaryLightGreen,
    content: @Composable () -> Unit
) {
    val colorSchemeList = when (currentColor) {
        primaryLightGreen ->  listOf(LightColorSchemeGreen, DarkColorSchemeGreen)
        primaryLightBlue -> listOf(LightColorSchemeBlue, DarkColorSchemeBlue)
        else ->  listOf(LightColorSchemeBrown, DarkColorSchemeBrown)
    }

    val colorScheme = when {
        darkTheme -> colorSchemeList[1]
        else -> colorSchemeList[0]
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}