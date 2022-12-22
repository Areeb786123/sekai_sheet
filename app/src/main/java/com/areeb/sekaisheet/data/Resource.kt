package com.areeb.sekaisheet.data

import okhttp3.ResponseBody

sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(
        val isNetworkError: Boolean,
        val errorCode: Int?,
        val errorBody: ResponseBody?,
        val unknownError: String? = null
    ) : Resource<Nothing>()
    data class Loading(val status: Boolean? = null, val tag: String? = null) :
        Resource<Nothing>()
}
