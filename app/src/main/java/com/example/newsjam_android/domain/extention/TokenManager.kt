package com.example.newsjam_android.domain.extention

import android.content.Context
import androidx.datastore.preferences.core.edit
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import androidx.datastore.preferences.core.stringPreferencesKey
import com.example.newsjam_android.ui.utils.extensions.dataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class TokenManager @Inject constructor(
    @ApplicationContext private val context : Context
) {
    private val dataStore = context.dataStore

    companion object{
        private val FIREBASE_TOKEN = stringPreferencesKey("user_token")

    }

    val getFireBaseToken: Flow<String?> = dataStore.data
        .map { preferences ->
            preferences[FIREBASE_TOKEN]
        }

    suspend fun saveFirebaseToken(token: String) {
        dataStore.edit { preferences ->
            preferences[FIREBASE_TOKEN] = token // 키를 사용하여 토큰 저장
        }
    }
}