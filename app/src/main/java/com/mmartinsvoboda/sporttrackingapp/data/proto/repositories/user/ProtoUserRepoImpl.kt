package com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.user

import android.content.Context
import androidx.datastore.core.DataStore
import com.mmartinsvoboda.sporttrackingapp.data.proto.serializers.userStore
import com.sample.android_sample_preference_datastore.UserStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProtoUserRepoImpl @Inject constructor(
    @ApplicationContext context: Context
) : ProtoUserRepo {
    private val protoUserStore: DataStore<UserStore> = context.userStore

    override suspend fun saveUserState(state: String) {
        protoUserStore.updateData { store ->
            store.toBuilder()
                .setUser(state)
                .build()
        }
    }

    override fun getUserState(): Flow<String> {
        return protoUserStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exception
                }
            }.map { protoBuilder ->
                protoBuilder.user
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    override fun getAllData(): Flow<UserStore> {
        return protoUserStore.data
            .catch { exception ->
                // dataStore.data throws an IOException when an error is encountered when reading data
                if (exception is IOException) {
                    emit(UserStore.getDefaultInstance())
                } else {
                    throw exception
                }
            }
            .distinctUntilChanged()
            .flowOn(Dispatchers.IO)
    }

    override suspend fun clearAllData() {
        protoUserStore.updateData {
            it.toBuilder().clear().build()
        }
    }
}