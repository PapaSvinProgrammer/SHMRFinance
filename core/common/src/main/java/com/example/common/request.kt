package com.example.common

suspend inline fun <reified T> request(
    block: () -> Result<T, NetworkError>
): Result<T, NetworkError> {
    var refresh = false
    var result: Result<T, NetworkError> =
        Result.Error(NetworkError.UNKNOWN)

    val response = block()

    response.onSuccess {
        result = Result.Success(it)
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