package com.example.data.internal.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.external.remote.PreferencesRepository
import com.example.data.external.remote.PreferencesRepository.Companion.DEFAULT_COLOR_RESULT
import com.example.data.external.remote.PreferencesRepository.Companion.DEFAULT_FREQUENCY_RESULT
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

internal class PreferencesRepositoryImpl @Inject constructor(
    private val context: Context
): PreferencesRepository {
    override suspend fun setCurrentAccountId(id: Int) {
        context.dataStore.edit {
            it[ACCOUNT_ID] = id
        }
    }

    override suspend fun setDarkTheme(state: Boolean) {
        context.dataStore.edit {
            it[DARK_THEME] = state
        }
    }

    override suspend fun setDefaultColor(argb: Int) {
        context.dataStore.edit {
            it[DEFAULT_COLOR] = argb
        }
    }

    override suspend fun setFrequencyValue(value: Int) {
        context.dataStore.edit {
            it[FREQUENCY_SYNC] = value
        }
    }

    override fun getCurrentAccountId(): Flow<Int?> {
        return context.dataStore.data.map {
            it[ACCOUNT_ID]
        }
    }

    override fun getDarkTheme(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[DARK_THEME] ?: false
        }
    }

    override fun getDefaultColor(): Flow<Int> {
        return context.dataStore.data.map {
            it[DEFAULT_COLOR] ?: DEFAULT_COLOR_RESULT
        }
    }

    override fun getFrequencyValue(): Flow<Int> {
        return context.dataStore.data.map {
            it[FREQUENCY_SYNC] ?: DEFAULT_FREQUENCY_RESULT
        }
    }

    private companion object {
        const val DATA_STORE_NAME = "SHMR_settings_name"
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)

        val ACCOUNT_ID = intPreferencesKey("account_id")
        val DARK_THEME = booleanPreferencesKey("dark_theme")
        val DEFAULT_COLOR = intPreferencesKey("current_color")
        val FREQUENCY_SYNC = intPreferencesKey("frequency_synchronization")
    }
}