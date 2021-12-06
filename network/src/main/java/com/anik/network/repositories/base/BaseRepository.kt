package com.anik.network.repositories.base

import com.anik.network.util.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response
import java.io.IOException

abstract class BaseRepository {

    protected suspend fun <T : Any> safeApiCall(dispatcher: CoroutineDispatcher = Dispatchers.IO, call: suspend () -> Response<T>): Resource<T> {
        var response: Response<T>? = null
        return withContext(dispatcher) {
            try {
                response = call()
            } catch (ioException: IOException) {
                return@withContext Resource.Error(ConnectionException())
            } catch (nullPointer: NullPointerException) {
                Resource.Error(nullPointer)
            } catch (throwable: Throwable) {
                return@withContext Resource.Error(ExceptionHandler.parseHttpException(throwable))
            }
            response.let {res->
                res?.let {
                    if (it.isSuccessful) {
                        response?.body()?.let { Resource.Success(it) } ?: Resource.Error(
                            EmptyResponseBodyException()
                        )
                    } else{
                        Resource.Error(UnknownException())
                    }
                } ?: Resource.Error(ConnectionException())
            }
        }
    }

}