package com.example.network.core

import io.ktor.client.statement.HttpResponse
import io.ktor.util.network.UnresolvedAddressException
import kotlinx.coroutines.ensureActive
import kotlinx.serialization.SerializationException
import kotlin.coroutines.coroutineContext

suspend inline fun <reified T> safeCall(
    execute: () -> HttpResponse
): Operation<T, NetworkRootError> {
    val response = try {
        execute()
    } catch (e: UnresolvedAddressException) {
        return Operation.Error(NetworkRootError.NO_INTERNET)
    } catch (e: SerializationException) {
        return Operation.Error(NetworkRootError.SERIALIZATION)
    } catch (e: Exception) {
        coroutineContext.ensureActive()
        return Operation.Error(NetworkRootError.UNKNOWN)
    }

    return responseToResult(response)
}