package com.danielys.pedulihiv.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class UserPreferences private constructor(private val dataStore: DataStore<Preferences>) {

    private val nameKey = stringPreferencesKey("name_key_pref")
    private val usernameKey = stringPreferencesKey("username_key_pref")
    private val photoKey = stringPreferencesKey("photo_key_pref")

    fun getName(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[nameKey] ?: ""
        }
    }

    fun getUsername(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[usernameKey] ?: ""
        }
    }

    fun getPhoto(): Flow<String> {
        return dataStore.data.map { preferences ->
            preferences[photoKey] ?: ""
        }
    }

    suspend fun setName(name: String) {
        dataStore.edit { preferences ->
            preferences[nameKey] = name
        }
    }

    suspend fun setUsername(username: String) {
        dataStore.edit { preferences ->
            preferences[usernameKey] = username
        }
    }

    suspend fun setPhoto(token: String) {
        dataStore.edit { preferences ->
            preferences[photoKey] = token
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: UserPreferences? = null

        fun getInstance(dataStore: DataStore<Preferences>): UserPreferences {
            return INSTANCE ?: synchronized(this) {
                val instance = UserPreferences(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}