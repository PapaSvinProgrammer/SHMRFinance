package com.example.corecomponent

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.utils.cancelAllJobs
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class AppViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _darkTheme = MutableStateFlow(false)
    val darkTheme = _darkTheme.asStateFlow()

    init {
        getDarkTheme()
    }

    private fun getDarkTheme() = launchWithoutOld(DARK_THEME_JOB) {
        preferencesRepository.getDarkTheme().collect {
            _darkTheme.value = it
        }
    }

    private companion object {
        const val DARK_THEME_JOB = "get_dark_theme"
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }
}