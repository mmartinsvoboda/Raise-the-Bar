package com.mmartinsvoboda.sporttrackingapp.common.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <DB, REMOTE> performGetOperation(
    crossinline fetchFromLocal: suspend () -> Flow<DB>,
    crossinline fetchFromRemote: suspend () -> Resource<REMOTE>,
    noinline processRemoteData: suspend (REMOTE) -> REMOTE = { it },
    crossinline syncRemoteData: suspend (local: DB, remote: REMOTE) -> Unit,
    crossinline onFetchFailed: (throwable: Throwable?) -> Unit = { _: Throwable? -> }
): Flow<Resource<DB>> = flow {
    val localData = fetchFromLocal.invoke().first()

    emit(Resource.loading(localData))

    fetchFromRemote.invoke().let { apiResponse ->
        when (apiResponse.status) {
            Resource.Status.SUCCESS -> {
                apiResponse.data?.let {
                    val processedData = processRemoteData(it)
                    syncRemoteData(fetchFromLocal.invoke().first(), processedData)
                }
                emitAll(
                    fetchFromLocal().map { dbData ->
                        Resource.success(dbData)
                    }
                )
            }
            Resource.Status.ERROR -> {
                onFetchFailed(apiResponse.throwable)
                emitAll(
                    fetchFromLocal().map {
                        Resource.error(
                            apiResponse.throwable!!,
                            it
                        )
                    }
                )
            }
            Resource.Status.LOADING -> Unit
        }
    }
}.flowOn(Dispatchers.IO).distinctUntilChanged()
