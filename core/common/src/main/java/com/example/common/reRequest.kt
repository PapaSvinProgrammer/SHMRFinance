package com.example.common

import kotlinx.coroutines.delay

suspend inline fun <reified T> reRequest(
    block: () -> Result<T>
): Result<T> {
    var count = 0
    var result: Result<T> = Result.failure(UnknownException())

    while (count != 3) {
        delay(2000)
        count++

        block().onSuccess {
            result = Result.success(it)
            count = 3
        }.onFailure {
            if (it !is ServerErrorException) {
                result = Result.failure(it)
                count = 3
            }
        }
    }

    return result
}