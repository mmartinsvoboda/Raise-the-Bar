package com.mmartinsvoboda.sporttrackingapp.data.proto.serializers

import android.content.Context
import androidx.datastore.core.CorruptionException
import androidx.datastore.core.DataStore
import androidx.datastore.core.Serializer
import androidx.datastore.dataStore
import com.google.protobuf.InvalidProtocolBufferException
import com.sample.android_sample_preference_datastore.SettingsStore
import java.io.InputStream
import java.io.OutputStream

private const val DEVEL_STORE_FILE_NAME = "settings.pb"

object SettingsStoreSerializer : Serializer<SettingsStore> {
    override val defaultValue: SettingsStore = SettingsStore.getDefaultInstance()

    override suspend fun readFrom(input: InputStream): SettingsStore {
        try {
            return SettingsStore.parseFrom(input)
        } catch (exception: InvalidProtocolBufferException) {
            throw CorruptionException("Cannot read proto.", exception)
        }
    }

    override suspend fun writeTo(t: SettingsStore, output: OutputStream) {
        t.writeTo(output)
    }
}

val Context.settingsStore: DataStore<SettingsStore> by dataStore(
    fileName = DEVEL_STORE_FILE_NAME,
    serializer = SettingsStoreSerializer
)

