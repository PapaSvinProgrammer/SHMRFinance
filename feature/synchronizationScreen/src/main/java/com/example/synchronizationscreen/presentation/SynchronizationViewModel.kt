package com.example.synchronizationscreen.presentation

import androidx.lifecycle.ViewModel
import com.example.data.external.remote.PreferencesRepository
import com.example.synchronizationscreen.domain.GetWorkLogInfo
import com.example.utils.manager.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import kotlin.math.round

internal class SynchronizationViewModel @Inject constructor(
    getWorkLogInfo: GetWorkLogInfo,
    private val preferencesRepository: PreferencesRepository
): ViewModel() {
    private val _frequencyClock = MutableStateFlow(PreferencesRepository.DEFAULT_FREQUENCY_RESULT)
    val frequencyClock = _frequencyClock.asStateFlow()
    val workLogInfo = getWorkLogInfo.execute()

    init {
        getFrequencyClock()
    }

    fun setFrequencyClock() = launchWithoutOld(SET_FREQUENCY_JOB) {
        preferencesRepository.setFrequencyValue(_frequencyClock.value)
    }

    fun updateFrequencyClock(value: Float) {
        _frequencyClock.value = round(value).toInt()
    }

    private fun getFrequencyClock() = launchWithoutOld(GET_FREQUENCY_JOB) {
        preferencesRepository.getFrequencyValue().collect {
            _frequencyClock.value = it
        }
    }

    private companion object {
        const val GET_FREQUENCY_JOB = "get_frequency_clock"
        const val SET_FREQUENCY_JOB = "set_frequency_clock"
    }
}