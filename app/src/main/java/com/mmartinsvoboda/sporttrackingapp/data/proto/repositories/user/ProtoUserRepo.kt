package com.mmartinsvoboda.sporttrackingapp.data.proto.repositories.user

import com.sample.android_sample_preference_datastore.UserStore
import kotlinx.coroutines.flow.Flow

interface ProtoUserRepo {
    suspend fun saveUserState(state: String)
    fun getUserState(): Flow<String>

    fun getAllData(): Flow<UserStore>
    suspend fun clearAllData()
}