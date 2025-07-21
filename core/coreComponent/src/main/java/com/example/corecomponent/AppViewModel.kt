package com.example.corecomponent

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.utils.manager.cancelAllJobs
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    private val _currentColor = MutableStateFlow(DEFAULT_COLOR)
    val darkTheme = _darkTheme.asStateFlow()
    val currentColor = _currentColor.asStateFlow()

    init {
        getDarkTheme()
        getCurrentColor()
    }

    private fun getDarkTheme() = launchWithoutOld(DARK_THEME_JOB) {
        preferencesRepository.getDarkTheme().collect {
            _darkTheme.value = it
        }
    }

    private fun getCurrentColor() = launchWithoutOld {
        preferencesRepository.getDefaultColor().collect {
            _currentColor.value = it
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    companion object {
        private const val DARK_THEME_JOB = "get_dark_theme"
        const val DEFAULT_COLOR = 0x00000000
    }
}