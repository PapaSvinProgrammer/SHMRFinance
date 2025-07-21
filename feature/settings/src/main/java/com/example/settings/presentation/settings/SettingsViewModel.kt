package com.example.settings.presentation.settings

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class SettingsViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    init {
        getDarkTheme()
    }

    fun setDarkTheme(state: Boolean) = launchWithoutOld(SET_DARK_THEME_JOB) {
        preferencesRepository.setDarkTheme(state)
    }

    private fun getDarkTheme() = launchWithoutOld(GET_DARK_THEME_JOB) {
        preferencesRepository.getDarkTheme().collect {
            _darkTheme.value = it
        }
    }

    private companion object {
        const val GET_DARK_THEME_JOB = "get_dark_theme"
        const val SET_DARK_THEME_JOB = "set_dark_theme"
    }
}