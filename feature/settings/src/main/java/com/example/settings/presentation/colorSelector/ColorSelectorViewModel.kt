package com.example.settings.presentation.colorSelector

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.utils.manager.cancelAllJobs
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class ColorSelectorViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _currentColor = MutableStateFlow(0)
    val currentColor = _currentColor.asStateFlow()

    init {
        getCurrentColor()
    }

    fun setCurrentColor(argb: Int) = launchWithoutOld(SET_CURRENT_COLOR_JOB) {
        preferencesRepository.setDefaultColor(argb)
    }

    private fun getCurrentColor() = launchWithoutOld(GET_CURRENT_COLOR_JOB) {
        preferencesRepository.getDefaultColor().collect {
            _currentColor.value = it
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val SET_CURRENT_COLOR_JOB = "set_current_color"
        const val GET_CURRENT_COLOR_JOB = "get_current_color"
    }
}