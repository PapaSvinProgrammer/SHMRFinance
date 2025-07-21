package com.example.data.external.remote

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setCurrentAccountId(id: Int)
    suspend fun setDarkTheme(state: Boolean)

    fun getCurrentAccountId(): Flow<Int?>
    fun getDarkTheme(): Flow<Boolean>
}