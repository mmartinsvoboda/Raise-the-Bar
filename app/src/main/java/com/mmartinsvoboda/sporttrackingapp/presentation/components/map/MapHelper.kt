package com.mmartinsvoboda.sporttrackingapp.presentation.components.map

import android.content.Context
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber
import java.io.IOException

object MapHelper {

    fun getAddressObjectFlow(
        address: String,
        context: Context
    ) = flow {
        val list = try {
            Geocoder(context).getFromLocationName(
                address,
                1
            )
        } catch (e: IOException) {
            emptyList()
        }

        Timber.d("Emitting ${list?.firstOrNull()}")
        emit(list?.firstOrNull())
    }.distinctUntilChanged().flowOn(Dispatchers.IO)

}