package com.example.corecomponent

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.utils.manager.cancelAllJobs
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AppUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getDarkTheme()
        getCurrentColor()
        getAuthState()
        getHapticNumber()
    }

    private fun getDarkTheme() = launchWithoutOld(DARK_THEME_JOB) {
        preferencesRepository.getDarkTheme().collect { darkTheme ->
            _uiState.update {
                it.copy(darkTheme = darkTheme)
            }
        }
    }

    private fun getCurrentColor() = launchWithoutOld(CURRENT_COLOR_JOB) {
        preferencesRepository.getDefaultColor().collect { color ->
            _uiState.update {
                it.copy(currentColor = color)
            }
        }
    }

    private fun getAuthState() = launchWithoutOld(AUTH_STATE_JOB) {
        preferencesRepository.getAuthorizationState().collect { authState ->
            _uiState.update {
                it.copy(authState = authState)
            }
        }
    }

    private fun getHapticNumber() = launchWithoutOld(HAPTIC_NUMBER_JOB) {
        preferencesRepository.getDefaultHaptic().collect { hapticNumber ->
            _uiState.update {
                it.copy(
                    hapticNumber = hapticNumber
                )
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    companion object {
        private const val DARK_THEME_JOB = "get_dark_theme"
        private const val CURRENT_COLOR_JOB = "get_current_color"
        private const val AUTH_STATE_JOB = "get_auth_state"
        private const val HAPTIC_NUMBER_JOB = "get_haptic_number"
        const val DEFAULT_COLOR = 0x00000000
    }
}