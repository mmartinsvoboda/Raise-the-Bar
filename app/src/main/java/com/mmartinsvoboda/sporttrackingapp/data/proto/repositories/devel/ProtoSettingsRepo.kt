package com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.devel

import com.sample.android_sample_preference_datastore.SettingsStore
import kotlinx.coroutines.flow.Flow

interface ProtoSettingsRepo {
    suspend fun saveUserSetDarkModePreferenceState(state: Boolean)
    fun getUserSetDarkModePreferenceState(): Flow<Boolean>

    suspend fun saveDarkModePreferenceState(state: Boolean)
    fun getDarkModePreferenceState(): Flow<Boolean>

    suspend fun savePreferredLocaleState(state: String)
    fun getPreferredLocaleState(): Flow<String>

    fun getAllData(): Flow<SettingsStore>
    suspend fun clearAllData()
}