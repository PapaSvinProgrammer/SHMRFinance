package com.example.network.common

import com.example.common.NetworkError
import com.example.common.Result
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T, NetworkError> {
    return when(response.status.value) {
        204 -> Result.Error(NetworkError.SUCCESS_DELETE_TRANSACTION)
        in 200..299 -> {
            try {
                Result.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> Result.Error(NetworkError.REQUEST_TIMEOUT)
        409 -> Result.Error(NetworkError.HAVE_TRANSACTION)
        500 -> Result.Error(NetworkError.SERVER_ERROR)
        else -> {
            Result.Error(NetworkError.UNKNOWN)
        }
    }
}