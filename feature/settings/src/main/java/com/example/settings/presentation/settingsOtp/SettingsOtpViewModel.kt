package com.example.settings.presentation.settingsOtp

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.utils.manager.cancelAllJobs
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class SettingsOtpViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _authState = MutableStateFlow(false)
    val authState = _authState.asStateFlow()

    init {
        getAuthState()
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

    private companion object {
        const val AUTH_STATE_JOB = "get_auth_state"
    }
}

