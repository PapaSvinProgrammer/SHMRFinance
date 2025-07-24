package com.example.charts.utils

import androidx.compose.ui.graphics.Color

fun generateColors(count: Int): List<Color> {
    return List(count) { index ->
        val hue = (index * 360f / count) % 360f
        Color.hsv(hue, 0.5f, 0.95f)
    }
}