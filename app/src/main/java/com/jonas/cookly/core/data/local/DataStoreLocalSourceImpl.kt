package com.jonas.cookly.core.data.local

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.jonas.cookly.core.domain.UserData
import com.jonas.cookly.core.util.logging.logError
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class DataStoreLocalSourceImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : DataStoreLocalSource {

    private object PreferencesKeys {
        val TOKEN_KEY = stringPreferencesKey("token")
        val USERNAME_KEY = stringPreferencesKey("username")
    }

    override fun getData(): Flow<UserData> {
        return dataStore.data.catch { error ->
            error.localizedMessage?.let { message ->
                logError("DATA_STORE_ERROR", message)
                emit(emptyPreferences())
            }
        }.map {
            UserData(
                token = it[PreferencesKeys.TOKEN_KEY] ?: "",
                userName = it[PreferencesKeys.USERNAME_KEY] ?: ""
            )
        }
    }

    override suspend fun saveData(userData: UserData) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.TOKEN_KEY] = userData.token.toString()
            preferences[PreferencesKeys.USERNAME_KEY] = userData.userName.toString()
        }
    }

    override suspend fun clearData() {
        dataStore.edit { preferences ->
            preferences.clear()
        }
    }

}
