package com.example.data.internal.remote

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.data.external.remote.PreferencesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

private const val DATA_STORE_NAME = "SHMR_settings_name"
private val ACCOUNT_ID = intPreferencesKey("account_id")
private val DARK_THEME = booleanPreferencesKey("dark_theme")
private val CURRENT_COLOR = intPreferencesKey("current_color")

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

    override suspend fun setCurrentColor(argb: Int) {
        context.dataStore.edit {
            it[CURRENT_COLOR] = argb
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

    override fun getCurrentColor(): Flow<Int> {
        return context.dataStore.data.map {
            it[CURRENT_COLOR] ?: DEFAULT_COLOR
        }
    }

    private companion object {
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = DATA_STORE_NAME)
        const val DEFAULT_COLOR = 0xFF2AE881.toInt()
    }
}