package com.example.network.core

import kotlinx.coroutines.delay

suspend inline fun <reified T> reRequest(
    block: () -> Operation<T, NetworkError>
): Operation<T, NetworkError> {
    var count = 0
    var result: Operation<T, NetworkError> = Operation.Error(NetworkError.SERVER_ERROR)

    while (count != 3) {
        delay(2000)
        count++

        block().onSuccess {
            result = Operation.Success(it)
            count = 3
        }.onError {
            if (it != NetworkError.SERVER_ERROR) {
                result = Operation.Error(it)
                count = 3
            }
        }
    }

    return result
}