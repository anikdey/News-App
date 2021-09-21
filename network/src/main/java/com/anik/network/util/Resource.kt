package com.anik.network.util

import com.anik.network.response.ErrorResponse

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class NetworkError<T>(message: String, data: T? = null) : Resource<T>(data, message)
    class GenericError(val code: Int? = null, val error: ErrorResponse? = null): Resource<Nothing>()
}