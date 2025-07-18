package com.example.room.internal

import com.example.utils.RoomThrowable

suspend inline fun <reified T> safeCall(
    execute: suspend () -> T
): Result<T> {
    return try {
        Result.success(execute())
    } catch (e: Throwable) {
        Result.failure(RoomThrowable(e.message))
    }
}