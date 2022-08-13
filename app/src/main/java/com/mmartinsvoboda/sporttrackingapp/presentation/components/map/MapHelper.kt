package com.mmartinsvoboda.sporttrackingapp.presentation.components.map

import android.content.Context
import android.location.Geocoder
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import timber.log.Timber

object MapHelper {

    fun getAddressObjectFlow(address: String, context: Context) = flow {
        val list = Geocoder(context).getFromLocationName(
            address, 1
        )

        Timber.d("Emitting ${list.firstOrNull()}")
        emit(list.firstOrNull())
    }.flowOn(Dispatchers.IO)

}