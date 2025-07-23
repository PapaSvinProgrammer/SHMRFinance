package com.example.data.external.remote

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setCurrentAccountId(id: Int)
    suspend fun setDarkTheme(state: Boolean)
    suspend fun setDefaultColor(argb: Int)
    suspend fun setFrequencyValue(value: Int)
    suspend fun setAuthorizationState(state: Boolean)

    fun getCurrentAccountId(): Flow<Int?>
    fun getDarkTheme(): Flow<Boolean>
    fun getDefaultColor(): Flow<Int>
    fun getFrequencyValue(): Flow<Int>
    fun getAuthorizationState(): Flow<Boolean>

    companion object {
        const val DEFAULT_COLOR_RESULT = 0xFF2AE881.toInt()
        const val DEFAULT_FREQUENCY_RESULT = 6
    }
}