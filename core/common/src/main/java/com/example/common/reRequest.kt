package com.example.common

import kotlinx.coroutines.delay

suspend inline fun <reified T> reRequest(
    block: () -> Result<T, NetworkError>
): Result<T, NetworkError> {
    var count = 0
    var result: Result<T, NetworkError> = Result.Error(NetworkError.SERVER_ERROR)

    while (count != 3) {
        delay(2000)
        count++

        block().onSuccess {
            result = Result.Success(it)
            count = 3
        }.onError {
            if (it != NetworkError.SERVER_ERROR) {
                result = Result.Error(it)
                count = 3
            }
        }
    }

    return result
}