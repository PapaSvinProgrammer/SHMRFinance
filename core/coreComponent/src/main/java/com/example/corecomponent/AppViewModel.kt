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
    private val _authState = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()
    val currentColor = _currentColor.asStateFlow()
    val authState = _authState.asStateFlow()

    init {
        getDarkTheme()
        getCurrentColor()
        getAuthState()
    }

    private fun getDarkTheme() = launchWithoutOld(DARK_THEME_JOB) {
        preferencesRepository.getDarkTheme().collect {
            _darkTheme.value = it
        }
    }

    private fun getCurrentColor() = launchWithoutOld(CURRENT_COLOR_JOB) {
        preferencesRepository.getDefaultColor().collect {
            _currentColor.value = it
        }
    }

    private fun getAuthState() = launchWithoutOld(AUTH_STATE_JOB) {
        preferencesRepository.getAuthorizationState().collect {
            _authState.value = it
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
        const val DEFAULT_COLOR = 0x00000000
    }
}