package com.example.shmrfinance.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.shmrfinance.domain.repository.PreferencesRepository
import jakarta.inject.Inject
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

private const val DATA_STORE_NAME = "SHMR_settings_name"
private val ACCOUNT_ID = intPreferencesKey("account_id")

class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context
): PreferencesRepository {
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

    override suspend fun setCurrentAccountId(id: Int) {
        context.dataStore.edit {
            it[ACCOUNT_ID] = id
        }
    }

    override fun getCurrentAccountId(): Flow<Int?> {
        return context.dataStore.data.map {
            it[ACCOUNT_ID]
        }
    }
}