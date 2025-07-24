package com.example.settings.presentation.haptic

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.settings.presentation.widget.model.HapticUiState
import com.example.utils.manager.cancelAllJobs
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

internal class HapticViewModel @Inject constructor(
    private val preferencesRepository: PreferencesRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(HapticUiState())
    val uiState = _uiState.asStateFlow()

    init {
        getHapticNumber()
    }

    fun updateSwitchChecked(state: Boolean) {
        _uiState.update {
            it.copy(
                switchChecked = state
            )
        }
    }

    fun setHapticNumber(number: Int) = launchWithoutOld(SET_HAPTIC_NUMBER_JOB) {
        preferencesRepository.setDefaultHaptic(number)
    }

    private fun getHapticNumber() = launchWithoutOld(GET_HAPTIC_NUMBER_JOB) {
        preferencesRepository.getDefaultHaptic().collect { number ->
            _uiState.update {
                it.copy(
                    hapticNumber = number
                )
            }

            if (number != -1) {
                _uiState.update {
                    it.copy(
                        switchChecked = true
                    )
                }
            }
        }
    }

    override fun onCleared() {
        cancelAllJobs()
        super.onCleared()
    }

    private companion object {
        const val GET_HAPTIC_NUMBER_JOB = "get_haptic_number"
        const val SET_HAPTIC_NUMBER_JOB = "set_haptic_number"
    }
}