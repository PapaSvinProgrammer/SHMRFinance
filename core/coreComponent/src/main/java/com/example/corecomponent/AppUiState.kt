package com.example.corecomponent

data class AppUiState(
    val darkTheme: Boolean = false,
    val currentColor: Int = 0x00000000,
    val authState: Boolean = false,
    val hapticNumber: Int = -1
)