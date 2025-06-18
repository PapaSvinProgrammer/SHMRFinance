package com.example.shmrfinance.domain.repository

import kotlinx.coroutines.flow.Flow

interface PreferencesRepository {
    suspend fun setCurrentAccountId(id: Int)
    fun getCurrentAccountId(): Flow<Int?>
}