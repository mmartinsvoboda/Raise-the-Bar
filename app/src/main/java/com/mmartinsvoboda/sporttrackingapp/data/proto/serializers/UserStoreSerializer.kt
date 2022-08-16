package com.mmartinsvoboda.sporttrackingapp.data.proto.serializers

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.sample.android_sample_preference_datastore.UserStore
import java.io.InputStream
import java.io.OutputStream

private const val USER_DATA_STORE_FILE_NAME = "user.pb"

object UserStoreSerializer : Serializer<UserStore> {
    override val defaultValue: UserStore = UserStore.getDefaultInstance()
    override suspend fun readFrom(input: InputStream): UserStore {
        try {
            return UserStore.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException(
                "Cannot read proto.",
                exception
            )
        }
    }

    override suspend fun writeTo(
        t: UserStore,
        output: OutputStream
    ) {
        t.writeTo(output)
    }
}

val Context.userStore: DataStore<UserStore> by dataStore(
    fileName = USER_DATA_STORE_FILE_NAME,
    serializer = UserStoreSerializer
)

