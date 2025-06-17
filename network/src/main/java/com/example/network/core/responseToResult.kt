package com.example.network.core

import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Operation<T, NetworkRootError> {
    return when(response.status.value) {
        204 -> Operation.Error(NetworkRootError.SUCCESS_DELETE_TRANSACTION)
        in 200..299 -> {
            try {
                Operation.Success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Operation.Error(NetworkRootError.SERIALIZATION)
            }
        }
        408 -> Operation.Error(NetworkRootError.REQUEST_TIMEOUT)
        500 -> Operation.Error(NetworkRootError.SERVER_ERROR)
        else -> Operation.Error(NetworkRootError.UNKNOWN)
    }
}