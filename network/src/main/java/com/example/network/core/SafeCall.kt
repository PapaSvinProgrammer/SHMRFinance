package com.example.network.core

import android.util.Log
import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Operation<T, NetworkError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Operation.Error(NetworkError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Operation.Error(NetworkError.SERIALIZATION)
    } catch (e: Exception) {
        Log.d("RRRR", e.message.toString())
        coroutineContext.ensureActive()
        return Operation.Error(NetworkError.UNKNOWN)
    }

    return responseToResult(response)
}