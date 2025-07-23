package com.example.settings.presentation.widget.model

import androidx.core.view.HapticFeedbackConstantsCompat

internal sealed class HapticItem(
    val subtitle: String,
    val text: String,
    val numberVibration: Int
) {
    data object KeyboardHaptic : HapticItem(
        subtitle = "1",
        text = "Как клавиатура",
        numberVibration = HapticFeedbackConstantsCompat.KEYBOARD_PRESS
    )

    data object LongHaptic : HapticItem(
        subtitle = "2",
        text = "Длительное",
        numberVibration = HapticFeedbackConstantsCompat.LONG_PRESS
    )

    data object ClockTickHaptic : HapticItem(
        subtitle = "3",
        text = "Тик часов",
        numberVibration = HapticFeedbackConstantsCompat.CLOCK_TICK
    )

}

internal val hapticsList = listOf(
    HapticItem.KeyboardHaptic,
    HapticItem.LongHaptic,
    HapticItem.ClockTickHaptic
)