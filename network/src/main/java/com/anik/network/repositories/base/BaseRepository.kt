package com.anik.network.repositories.base

import com.anik.network.response.ErrorResponse
import com.anik.network.util.Resource
import com.squareup.moshi.Moshi
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

abstract class BaseRepository {

    //https://medium.com/@douglas.iacovelli/how-to-handle-errors-with-retrofit-and-coroutines-33e7492a912
    //https://codinginfinite.com/kotlin-coroutine-call-adapter-retrofit/

    suspend fun <T> safeApiCall(dispatcher: CoroutineDispatcher, apiCall: suspend () -> T): Resource<out T> {
        return withContext(dispatcher) {
            try {
                Resource.Success(apiCall.invoke())
            } catch (ex: Throwable) {
                when(ex) {
                    is IOException -> Resource.NetworkError("Check your internet connection")
                    is HttpException -> {
                        val code = ex.code()
                        Resource.GenericError(code, ErrorResponse(ex.message))
                    }
                    else -> {
                        Resource.GenericError(null, ErrorResponse(ex.message))
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = Moshi.Builder().build().adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }

}