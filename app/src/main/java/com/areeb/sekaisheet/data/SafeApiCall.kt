package com.areeb.sekaisheet.data

import com.areeb.sekaisheet.utils.NoInternetException
import retrofit2.HttpException
import java.net.SocketTimeoutException

interface SafeApiCall {
    suspend fun <T> safeApiCall(apiCall: suspend () -> T): Resource<T> {
        return try {
            Resource.Success(apiCall.invoke())
        } catch (throwable: Throwable) {
            when (throwable) {
                is HttpException -> {
                    Resource.Error(
                        false,
                        throwable.code(),
                        throwable.response()?.errorBody(),
                    )
                }
                is NoInternetException, is SocketTimeoutException -> Resource.Error(
                    true,
                    null,
                    null,
                )
                else -> Resource.Error(false, null, null)
            }
        }
    }
}
