package com.example.network.internal.common

import android.util.Log
import com.example.common.NoInternetException
import com.example.common.SerializationExceptionCustom
import com.example.common.UnknownException
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
        Log.d("RRRR", e.message.toString())
        return Result.failure(SerializationExceptionCustom())
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Result.failure(UnknownException())
    }

    return responseToResult(response)
}