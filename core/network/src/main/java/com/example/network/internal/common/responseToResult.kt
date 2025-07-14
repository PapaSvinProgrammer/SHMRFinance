package com.example.network.internal.common

import com.example.utils.HaveTransactionException
import com.example.utils.RequestTimeoutException
import com.example.utils.SerializationExceptionCustom
import com.example.utils.ServerErrorException
import com.example.utils.SuccessDeleteTransactionException
import com.example.utils.UnknownException
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