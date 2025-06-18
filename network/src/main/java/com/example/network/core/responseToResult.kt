package com.example.network.core

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Operation<T, NetworkError> {
    return when(response.status.value) {
        204 -> Operation.Error(NetworkError.SUCCESS_DELETE_TRANSACTION)
        in 200..299 -> {
            try {
                Operation.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Operation.Error(NetworkError.SERIALIZATION)
            }
        }
        408 -> Operation.Error(NetworkError.REQUEST_TIMEOUT)
        409 -> Operation.Error(NetworkError.HAVE_TRANSACTION)
        500 -> Operation.Error(NetworkError.SERVER_ERROR)
        else -> {
            Operation.Error(NetworkError.UNKNOWN)
        }
    }
}