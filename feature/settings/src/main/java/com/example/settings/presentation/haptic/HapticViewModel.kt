package com.example.settings.presentation.haptic

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import javax.inject.Inject

internal class HapticViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {

}