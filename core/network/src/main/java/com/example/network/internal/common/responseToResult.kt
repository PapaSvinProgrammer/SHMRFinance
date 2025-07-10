package com.example.network.internal.common

import com.example.common.HaveTransactionException
import com.example.common.RequestTimeoutException
import com.example.common.SerializationExceptionCustom
import com.example.common.ServerErrorException
import com.example.common.SuccessDeleteTransactionException
import com.example.common.UnknownException
import io.ktor.client.call.NoTransformationFoundException
import io.ktor.client.call.body
import io.ktor.client.statement.HttpResponse

internal suspend inline fun <reified T> responseToResult(
    response: HttpResponse
): Result<T> {
    return when(response.status.value) {
        204 -> Result.failure(SuccessDeleteTransactionException())
        in 200..299 -> {
            try {
                Result.success(response.body<T>())
            } catch (e: NoTransformationFoundException) {
                Result.failure(SerializationExceptionCustom())
            }
        }
        408 -> Result.failure(RequestTimeoutException())
        409 -> Result.failure(HaveTransactionException())
        in 500..599 -> Result.failure(ServerErrorException())
        else -> {
            Result.failure(UnknownException())
        }
    }
}