package com.mmartinsvoboda.sporttrackingapp.common.data

import android.accounts.AuthenticatorException
import okio.IOException
import retrofit2.Response
import timber.log.Timber

abstract class BaseDataSource {

    protected suspend fun <T> getResult(
        name: String,
        call: suspend () -> Response<T>
    ): Resource<T> {
        try {
            val response = call()

            if (response.isSuccessful) {
                response.body()?.let { body ->
                    return Resource.success(body)
                }
            }

            return when (response.code()) {
                401 -> error(
                    name,
                    AuthenticatorException("${response.code()} - ${response.message()}")
                )
                else -> error(
                    name,
                    IOException("${response.code()} - ${response.message()}")
                )
            }
        } catch (e: Exception) {
            return error(name, e)
        }
    }

    private fun <T> error(
        name: String,
        throwable: Throwable,
        shouldLog: Boolean = true
    ): Resource<T> {
        if (shouldLog) Timber.e(throwable, "Network call $name has failed")
        return Resource.error(throwable)
    }

}