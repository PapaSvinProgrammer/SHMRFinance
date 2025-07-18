package com.example.synchronizationscreen.presentation

import androidx.lifecycle.ViewModel
import com.example.synchronizationscreen.domain.GetWorkLogInfo
import javax.inject.Inject

internal class SynchronizationViewModel @Inject constructor(
    getWorkLogInfo: GetWorkLogInfo
): ViewModel() {
    val workLogInfo = getWorkLogInfo.execute()

    private companion object {
        const val WORK_INFO_JOB = "get_work_info"
    }
}