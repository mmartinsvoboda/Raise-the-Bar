package com.mmartinsvoboda.sporttrackingapp.common.data

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*

inline fun <DB, REMOTE, FINAL> performGetOperation(
    crossinline fetchFromLocal: suspend () -> Flow<DB>,
    crossinline shouldFetchFromRemote: (DB) -> Boolean,
    crossinline fetchFromRemote: suspend () -> Resource<REMOTE>,
    noinline processRemoteData: suspend (REMOTE) -> DB,
    noinline processFinalData: suspend (DB) -> FINAL,
    crossinline syncRemoteData: suspend (local: DB, remote: DB) -> Unit
): Flow<Resource<FINAL>> = flow {
    val localData = fetchFromLocal.invoke().first()

    emit(Resource.loading(processFinalData(localData)))

    if (shouldFetchFromRemote(localData)) {
        fetchFromRemote.invoke().let { apiResponse ->
            when (apiResponse.status) {
                Resource.Status.SUCCESS -> {
                    apiResponse.data?.let {
                        val processedRemoteData = processRemoteData(it)
                        val localDataForSync = fetchFromLocal.invoke().first()
                        syncRemoteData(localDataForSync, processedRemoteData)
                    }
                    emitAll(
                        fetchFromLocal().map { dbData ->
                            Resource.success(processFinalData(dbData))
                        }
                    )
                }
                Resource.Status.ERROR -> {
                    emitAll(
                        fetchFromLocal().map {
                            Resource.error(
                                apiResponse.throwable!!,
                                processFinalData(it)
                            )
                        }
                    )
                }
                Resource.Status.LOADING -> Unit
            }
        }
    } else {
        emit(Resource.success(processFinalData(localData)))
    }
}.flowOn(Dispatchers.IO).distinctUntilChanged()
