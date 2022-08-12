package com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.devel

import android.content.Context
import androidx.datastore.core.DataStore
import com.mmartinsvoboda.sporttrackingapp.data.proto.serializers.settingsStore
import com.sample.android_sample_preference_datastore.SettingsStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProtoSettingsRepoImpl @Inject constructor(
    @ApplicationContext context: Context
) : ProtoSettingsRepo {
    private val protoDataStore: DataStore<SettingsStore> = context.settingsStore

    override suspend fun saveUserSetDarkModePreferenceState(state: Boolean) {
        protoDataStore.updateData { store ->
            store.toBuilder()
                .setUserSetDarkModePreference(state)
                .build()
        }
    }

    override fun getUserSetDarkModePreferenceState(): Flow<Boolean> {
        return protoDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(SettingsStore.getDefaultInstance())
                } else {
                    throw exception
                }
            }.map { protoBuilder ->
                protoBuilder.userSetDarkModePreference
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun saveDarkModePreferenceState(state: Boolean) {
        protoDataStore.updateData { store ->
            store.toBuilder()
                .setDarkModePreference(state)
                .build()
        }
    }

    override fun getDarkModePreferenceState(): Flow<Boolean> {
        return protoDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(SettingsStore.getDefaultInstance())
                } else {
                    throw exception
                }
            }.map { protoBuilder ->
                protoBuilder.darkModePreference
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun savePreferredLocaleState(state: String) {
        protoDataStore.updateData { store ->
            store.toBuilder()
                .setPreferredLocale(state)
                .build()
        }
    }

    override fun getPreferredLocaleState(): Flow<String> {
        return protoDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(SettingsStore.getDefaultInstance())
                } else {
                    throw exception
                }
            }.map { protoBuilder ->
                protoBuilder.preferredLocale
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    override fun getAllData(): Flow<SettingsStore> {
        return protoDataStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(SettingsStore.getDefaultInstance())
                } else {
                    throw exception
                }
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun clearAllData() {
        protoDataStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}