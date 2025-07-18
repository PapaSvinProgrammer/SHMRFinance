package com.example.synchronizationscreen.presentation

import androidx.lifecycle.ViewModel
import androidx.work.WorkInfo
import com.example.synchronizationscreen.domain.GetWorkManagerInfo
import com.example.utils.launchWithoutOld
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

internal class SynchronizationViewModel @Inject constructor(
    private val getWorkManagerInfo: GetWorkManagerInfo
): ViewModel() {
    private val _workData = MutableStateFlow<List<WorkInfo>>(listOf())
    val workInfo = _workData.asStateFlow()

    fun getWorkInfo() = launchWithoutOld(WORK_INFO_JOB) {
        _workData.value = getWorkManagerInfo.execute(Unit)
    }

    private companion object {
        const val WORK_INFO_JOB = "get_work_info"
    }
}