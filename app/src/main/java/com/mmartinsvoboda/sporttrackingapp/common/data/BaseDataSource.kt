package com.mmartinsvoboda.sporttrackingapp.common.data

import android.accounts.AuthenticatorException
import com.mmartinsvoboda.sporttrackingapp.common.toGsonString
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
                    AuthenticatorException(
                        "${response.code()} - ${response.message()} - ${
                            response.body().toGsonString()
                        }"
                    )
                )
                else -> error(
                    name,
                    IOException(
                        "${response.code()} - ${response.message()} - ${
                            response.body().toGsonString()
                        }"
                    )
                )
            }
        } catch (e: Exception) {
            return error(
                name,
                e
            )
        }
    }

    private fun <T> error(
        name: String,
        throwable: Throwable,
        shouldLog: Boolean = true
    ): Resource<T> {
        if (shouldLog) Timber.e(
            throwable,
            "Network call $name has failed"
        )
        return Resource.error(throwable)
    }

}