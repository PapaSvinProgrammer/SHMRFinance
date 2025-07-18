package com.example.network.internal.common

import com.example.utils.NoInternetException
import com.example.utils.SerializationExceptionCustom
import com.example.utils.UnknownException
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

internal suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Result<T> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Result.failure(NoInternetException())
    } catch (e: SerializationException) {
        return Result.failure(SerializationExceptionCustom())
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.failure(UnknownException())
    }

    return responseToResult(response)
}