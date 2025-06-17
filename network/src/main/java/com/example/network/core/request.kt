package com.example.network.core

suspend inline fun <reified T> request(
    block: () -> Operation<T, NetworkError>
): Operation<T, NetworkError> {
    var refresh = false
    var result: Operation<T, NetworkError> = Operation.Error(NetworkError.UNKNOWN)

    val response = block()

    response.onSuccess {
        result = Operation.Success(it)
    }.onError {
        if (it == NetworkError.SERVER_ERROR) {
            refresh = true
        }
    }

    if (refresh) {
        return reRequest { block() }
    }

    return result
}