package com.example.data.extensions

import android.content.Context
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.data.extensions.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager @Inject constructor(
    @ApplicationContext private val context : Context
) {
    private val dataStore = context.dataStore

    companion object{
        private val FIREBASE_TOKEN = stringPreferencesKey("fcm_token")
        private val ACCESS_TOKEN_KEY = stringPreferencesKey("access_token")

    }

    val getFireBaseToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[FIREBASE_TOKEN]
        }

    suspend fun saveFirebaseToken(token: String) {
        dataStore.edit { preferences ->
            preferences[FIREBASE_TOKEN] = token
        }
    }

    val getAccessToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[ACCESS_TOKEN_KEY]
        }

    suspend fun saveAccessToken(token: String) {
        dataStore.edit { preferences ->
            preferences[ACCESS_TOKEN_KEY] = token
        }
    }
}