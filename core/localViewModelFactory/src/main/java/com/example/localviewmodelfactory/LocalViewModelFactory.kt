package com.example.localviewmodelfactory

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.lifecycle.ViewModelProvider

val LocalViewModelFactory = staticCompositionLocalOf<ViewModelProvider.Factory> {
    error("No viewModelFactory provided!")
}